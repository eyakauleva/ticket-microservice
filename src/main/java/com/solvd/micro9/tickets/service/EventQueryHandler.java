package com.solvd.micro9.tickets.service;

import com.solvd.micro9.tickets.domain.aggregate.Event;
import com.solvd.micro9.tickets.domain.query.ListEventQuery;
import reactor.core.publisher.Flux;

public interface EventQueryHandler {

    Flux<Event> getAll();

    Flux<Event> findByUserId(ListEventQuery query);

}
