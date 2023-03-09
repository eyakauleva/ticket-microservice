package com.solvd.micro9.tickets.service;

import com.solvd.micro9.tickets.domain.Ticket;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TicketService {

    Mono<Ticket> create(Ticket ticket);

    Flux<Ticket> getAll();

}
