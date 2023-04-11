package com.solvd.micro9.tickets.service;

import com.solvd.micro9.tickets.domain.aggregate.Event;
import com.solvd.micro9.tickets.domain.es.Es;
import reactor.core.publisher.Mono;

public interface EventService {

    Mono<Event> create(Es eventStore);

}
