package com.solvd.micro9.tickets.service;

import com.solvd.micro9.tickets.domain.Ticket;
import reactor.core.publisher.Flux;

public interface EsTicketQueryHandler {

    Flux<Ticket> getAll();

}
