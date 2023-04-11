package com.solvd.micro9.tickets.service.impl;

import com.google.gson.Gson;
import com.solvd.micro9.tickets.domain.aggregate.Ticket;
import com.solvd.micro9.tickets.domain.cache.TicketCache;
import com.solvd.micro9.tickets.domain.command.CreateTicketCommand;
import com.solvd.micro9.tickets.domain.command.DeleteTicketsUserByUserIdCommand;
import com.solvd.micro9.tickets.domain.command.ProcessTicketUpdateCommand;
import com.solvd.micro9.tickets.domain.es.EsStatus;
import com.solvd.micro9.tickets.domain.es.EsTicket;
import com.solvd.micro9.tickets.domain.es.EsType;
import com.solvd.micro9.tickets.domain.exception.ResourceDoesNotExistException;
import com.solvd.micro9.tickets.messaging.KfProducer;
import com.solvd.micro9.tickets.persistence.eventstore.EsEventRepository;
import com.solvd.micro9.tickets.persistence.eventstore.EsTicketRepository;
import com.solvd.micro9.tickets.service.DbsSynchronizer;
import com.solvd.micro9.tickets.service.EsTicketCommandHandler;
import com.solvd.micro9.tickets.service.cache.RedisConfig;
import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ReactiveHashOperations;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import reactor.util.function.Tuple2;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class EsTicketCommandHandlerImpl implements EsTicketCommandHandler {

    private final EsTicketRepository esTicketRepository;
    private final EsEventRepository esEventRepository;
    private final KfProducer producer;
    private final ReactiveHashOperations<String, Long, TicketCache> cache;
    private final DbsSynchronizer synchronizer;

    @Autowired
    public EsTicketCommandHandlerImpl(EsTicketRepository esTicketRepository,
                                      EsEventRepository esEventRepository,
                                      KfProducer producer,
                                      final ReactiveRedisOperations<String, TicketCache> operations,
                                      DbsSynchronizer synchronizer) {
        this.esTicketRepository = esTicketRepository;
        this.esEventRepository = esEventRepository;
        this.producer = producer;
        this.cache = operations.opsForHash();
        this.synchronizer = synchronizer;
    }

    @Transactional
    @Override
    public Mono<EsTicket> apply(CreateTicketCommand command) {
        String payload = new Gson().toJson(command.getTicket());
        EsTicket event = EsTicket.builder()
                .type(EsType.TICKET_CREATED)
                .time(LocalDateTime.now())
                .createdBy(command.getCommandBy())
                .entityId(UUID.randomUUID().toString())
                .payload(payload)
                .status(EsStatus.SUBMITTED)
                .build();
        return esEventRepository.findByEntityId(command.getTicket().getEventId())
                .collectList()
                .map(esEventsList -> {
                    if (esEventsList.isEmpty()) {
                        throw new ResourceDoesNotExistException(
                                "Event [id=" + command.getTicket().getEventId() + "] does not exist"
                        );
                    } else {
                        return esEventsList;
                    }
                })
                .zipWith(esTicketRepository.save(event))
                .map(Tuple2::getT2)
                .doOnSuccess(synchronizer::sync);
    }

    @Override
    public void apply(DeleteTicketsUserByUserIdCommand command) {
        List<EsTicket> esTicketList = new ArrayList<>();
        final boolean[] isStreamCompleted = {false};
        esTicketRepository.findAll()
                .filter(esTicket -> {
                    Ticket ticket = new Gson().fromJson(esTicket.getPayload(), Ticket.class);
                    return esTicket.getType().equals(EsType.TICKET_CREATED)
                            && command.getUserId().equals(ticket.getUserId());
                })
                .doOnNext(esTicket -> {
                    String template = "{\"userId\":\"%s\"}";
                    EsTicket event = EsTicket.builder()
                            .type(EsType.TICKET_USER_DELETED)
                            .time(LocalDateTime.now())
                            .createdBy(command.getCommandBy())
                            .entityId(esTicket.getEntityId())
                            .payload(String.format(template, command.getUserId()))
                            .status(EsStatus.SUBMITTED)
                            .build();
                    esTicketList.add(event);
                })
                .doOnComplete(() -> isStreamCompleted[0] = true)
                .subscribeOn(Schedulers.boundedElastic())
                .subscribe();
        while (!isStreamCompleted[0]) {
        } //TODO is there a better way to do?
        Flux.fromIterable(esTicketList)
                .flatMap(esTicket -> esTicketRepository.save(esTicket)
                        .doOnSuccess(event -> {
                            TicketCache ticketCache = new TicketCache(
                                    event.getId(),
                                    command.getUserId()
                            );
                            cache.put(RedisConfig.CACHE_KEY, event.getId(), ticketCache)
                                    .subscribeOn(Schedulers.boundedElastic())
                                    .subscribe();
                            producer.send("New event", event);
                            synchronizer.sync(event);
                        })
                )
                .subscribeOn(Schedulers.boundedElastic())
                .subscribe();
    }

    @Override
    public void apply(ProcessTicketUpdateCommand command) {
        cache.values(RedisConfig.CACHE_KEY)
                .filter(ticketCache -> command.getUserId().equals(ticketCache.getUserId()))
                .map(ticketCache -> {
                    cache.remove(RedisConfig.CACHE_KEY, ticketCache.getId())
                            .subscribeOn(Schedulers.boundedElastic())
                            .subscribe();
                    return ticketCache;
                })
                .subscribeOn(Schedulers.boundedElastic())
                .subscribe();
    }

    @PreDestroy
    private void cleanCache() {
        cache.delete(RedisConfig.CACHE_KEY).block();
    }

}
