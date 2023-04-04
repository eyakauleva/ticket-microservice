package com.solvd.micro9.tickets.service.impl;

import com.google.gson.Gson;
import com.solvd.micro9.tickets.domain.aggregate.Ticket;
import com.solvd.micro9.tickets.domain.command.CreateTicketCommand;
import com.solvd.micro9.tickets.domain.command.SetTicketsUserIdToNullByUserIdCommand;
import com.solvd.micro9.tickets.domain.es.EsEventType;
import com.solvd.micro9.tickets.domain.es.EsTicket;
import com.solvd.micro9.tickets.domain.exception.ResourceDoesNotExistException;
import com.solvd.micro9.tickets.messaging.KfProducer;
import com.solvd.micro9.tickets.persistence.eventstore.EsEventRepository;
import com.solvd.micro9.tickets.persistence.eventstore.EsTicketRepository;
import com.solvd.micro9.tickets.service.EsTicketCommandHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class EsTicketCommandHandlerImpl implements EsTicketCommandHandler {

    private final EsTicketRepository esTicketRepository;
    private final EsEventRepository esEventRepository;
    private final KfProducer producer;

    @Transactional
    @Override
    public Mono<EsTicket> apply(CreateTicketCommand command) {
        String payload = new Gson().toJson(command.getTicket());
        EsTicket event = EsTicket.builder()
                .type(EsEventType.TICKET_CREATED)
                .time(LocalDateTime.now())
                .createdBy(command.getCommandBy())
                .entityId(UUID.randomUUID().toString())
                .payload(payload)
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
                .doOnSuccess(esTicket -> producer.send("New event", esTicket));
    }

    @Override
    public void apply(SetTicketsUserIdToNullByUserIdCommand command) {
        List<EsTicket> esTicketList = new ArrayList<>();
        final boolean[] isStreamCompleted = {false};
        esTicketRepository.findAll()
                .filter(esTicket -> {
                    Ticket ticket = new Gson().fromJson(esTicket.getPayload(), Ticket.class);
                    return esTicket.getType().equals(EsEventType.TICKET_CREATED)
                            && command.getUserId().equals(ticket.getUserId());
                })
                .doOnNext(esTicket -> {
                    EsTicket event = EsTicket.builder()
                            .type(EsEventType.TICKET_USER_DELETED)
                            .time(LocalDateTime.now())
                            .createdBy(command.getCommandBy())
                            .entityId(esTicket.getEntityId())
                            .build();
                    esTicketList.add(event);
                })
                .doOnComplete(() -> isStreamCompleted[0] = true)
                .subscribe();

        while (!isStreamCompleted[0]) {
        } //TODO is there a better way to do?

        Flux.fromIterable(esTicketList)
                .flatMap(esTicket -> esTicketRepository.save(esTicket)
                        .doOnSuccess(event -> producer.send("New event", event))
                )
                .subscribe();
    }

}
