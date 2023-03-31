package com.solvd.micro9.tickets.service;

import com.solvd.micro9.tickets.domain.command.CreateTicketCommand;
import com.solvd.micro9.tickets.domain.command.SetTicketsUserIdToNullByUserIdCommand;
import com.solvd.micro9.tickets.domain.es.EsTicket;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface EsTicketCommandHandler {

    Mono<EsTicket> apply(CreateTicketCommand command);

    Flux<EsTicket> apply(SetTicketsUserIdToNullByUserIdCommand command);

}
