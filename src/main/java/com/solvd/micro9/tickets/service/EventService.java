package com.solvd.micro9.tickets.service;

import com.solvd.micro9.tickets.domain.Event;
import reactor.core.publisher.Flux;

public interface EventService {

    Flux<Event> getAll();

    Flux<Event> findByUserId(Long userId);

}
