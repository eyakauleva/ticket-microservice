package com.solvd.micro9.tickets.service;

import com.solvd.micro9.tickets.domain.Event;
import com.solvd.micro9.tickets.domain.query.ListEventQuery;
import reactor.core.publisher.Flux;

public interface EsEventQueryHandler {

    Flux<Event> getAll();

    Flux<Event> findByUserId(ListEventQuery query);

}
