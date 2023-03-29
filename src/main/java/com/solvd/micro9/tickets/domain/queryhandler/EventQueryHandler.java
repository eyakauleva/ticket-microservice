package com.solvd.micro9.tickets.domain.queryhandler;

import com.solvd.micro9.tickets.domain.Event;
import com.solvd.micro9.tickets.domain.query.EventQuery;
import com.solvd.micro9.tickets.domain.query.ListEventQuery;
import com.solvd.micro9.tickets.persistence.EventRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@RequiredArgsConstructor
public class EventQueryHandler {

    private final EventRepository eventRepository;

    @QueryHandler
    public Mono<Event> handle(EventQuery query){
        log.info("Handling EventQuery: {}", query);

        return eventRepository.findById(query.getUserId()); //TODO
    }

    @QueryHandler
    public Flux<Event> handle(ListEventQuery query){
        log.info("Handling ListEventQuery: {}", query);

        return eventRepository.findAll();
    }

}
