package com.solvd.micro9.tickets.service;

import com.solvd.micro9.tickets.domain.command.CreateTicketCommand;
import com.solvd.micro9.tickets.domain.command.DeleteTicketsUserByUserIdCommand;
import com.solvd.micro9.tickets.domain.command.ProcessTicketUpdateCommand;
import com.solvd.micro9.tickets.domain.es.EsTicket;
import reactor.core.publisher.Mono;

public interface EsTicketCommandHandler {

    Mono<EsTicket> apply(CreateTicketCommand command);

    void apply(DeleteTicketsUserByUserIdCommand command);

    void apply(ProcessTicketUpdateCommand command);

}
