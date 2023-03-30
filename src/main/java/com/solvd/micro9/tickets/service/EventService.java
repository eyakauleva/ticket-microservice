package com.solvd.micro9.tickets.service;

import com.solvd.micro9.tickets.domain.Event;
import com.solvd.micro9.tickets.domain.command.CreateEventCommand;
import com.solvd.micro9.tickets.domain.event.EventStoreEvents;
import com.solvd.micro9.tickets.domain.query.ListEventQuery;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface EventService {

    Flux<Event> getAll();

    Flux<Event> findByUserId(ListEventQuery query);

    Mono<EventStoreEvents> create(CreateEventCommand command);

}
