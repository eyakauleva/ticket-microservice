package com.solvd.micro9.tickets.service;

import com.solvd.micro9.tickets.domain.aggregate.Ticket;
import reactor.core.publisher.Flux;

public interface TicketQueryHandler {

    Flux<Ticket> getAll();

}
