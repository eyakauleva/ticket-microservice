package com.solvd.micro9.tickets.service;

import com.solvd.micro9.tickets.domain.Ticket;
import com.solvd.micro9.tickets.domain.command.CreateTicketCommand;
import com.solvd.micro9.tickets.domain.command.SetTicketsUserIdToNullByUserIdCommand;
import com.solvd.micro9.tickets.domain.event.EventStoreTickets;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TicketService {

    Mono<EventStoreTickets> create(CreateTicketCommand command);

    Flux<Ticket> getAll();

    Mono<EventStoreTickets> updateDeletedUserTickets(SetTicketsUserIdToNullByUserIdCommand command);

}
