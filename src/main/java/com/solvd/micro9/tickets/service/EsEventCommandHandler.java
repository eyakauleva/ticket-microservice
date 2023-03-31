package com.solvd.micro9.tickets.service;

import com.solvd.micro9.tickets.domain.command.CreateEventCommand;
import com.solvd.micro9.tickets.domain.es.EsEvent;
import reactor.core.publisher.Mono;

public interface EsEventCommandHandler {

    Mono<EsEvent> apply(CreateEventCommand command);

}
