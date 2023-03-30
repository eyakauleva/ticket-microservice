package com.solvd.micro9.tickets.service.impl;

import com.google.gson.Gson;
import com.solvd.micro9.tickets.domain.Ticket;
import com.solvd.micro9.tickets.domain.command.CreateTicketCommand;
import com.solvd.micro9.tickets.domain.command.SetTicketsUserIdToNullByUserIdCommand;
import com.solvd.micro9.tickets.domain.event.EventStoreTickets;
import com.solvd.micro9.tickets.domain.event.EventType;
import com.solvd.micro9.tickets.domain.exception.ResourceDoesNotExistException;
import com.solvd.micro9.tickets.persistence.EventRepository;
import com.solvd.micro9.tickets.persistence.TicketRepository;
import com.solvd.micro9.tickets.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;
    private final EventRepository eventRepository;

    @Transactional
    @Override
    public Mono<EventStoreTickets> create(CreateTicketCommand command) {
        String payload = new Gson().toJson(command.getTicket());
        EventStoreTickets event = EventStoreTickets.builder()
                .type(EventType.TICKET_CREATED)
                .time(LocalDateTime.now())
                .createdBy(command.getCommandBy())
                .entityId(UUID.randomUUID().toString())
                .payload(payload)
                .build();
        return eventRepository.findByEntityId(command.getTicket().getEventId())
                .switchIfEmpty(
                        Mono.error(
                                new ResourceDoesNotExistException(
                                        "Event [id=" + command.getTicket().getEventId() + "] does not exist")
                        )
                )
                .zipWith(ticketRepository.save(event))
                .map(Tuple2::getT2);
    }

    @Override
    public Flux<Ticket> getAll() {
        return ticketRepository.findAll()
                .map(ticketStore -> {
                    Ticket ticket = new Gson().fromJson(ticketStore.getPayload(), Ticket.class);
                    ticket.setId(ticketStore.getEntityId());
                    return ticket;
                });
    }

    @Transactional
    @Override
    public Mono<EventStoreTickets> updateDeletedUserTickets(SetTicketsUserIdToNullByUserIdCommand command) {
        String payload = new Gson().toJson(command.getUserId());
        EventStoreTickets event = EventStoreTickets.builder()
                .type(EventType.TICKETS_USER_ID_SET_TO_NULL_BY_USER_ID)
                .time(LocalDateTime.now())
                .createdBy(command.getCommandBy())
                .entityId(null) //TODO set patch of ids?
                .payload(payload)
                .build();
        return ticketRepository.save(event);
    }

}
