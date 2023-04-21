package com.solvd.micro9.tickets.service;

import com.solvd.micro9.tickets.domain.aggregate.Ticket;
import com.solvd.micro9.tickets.domain.es.Es;
import reactor.core.publisher.Mono;

public interface TicketService {

    Mono<Ticket> create(Es eventStore);

    Mono<Ticket> setUserToNull(Es eventStore);

}
