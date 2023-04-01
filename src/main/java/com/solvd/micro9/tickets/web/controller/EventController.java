package com.solvd.micro9.tickets.web.controller;

import com.solvd.micro9.tickets.domain.aggregate.Event;
import com.solvd.micro9.tickets.domain.command.CreateEventCommand;
import com.solvd.micro9.tickets.domain.es.EsEvent;
import com.solvd.micro9.tickets.domain.query.ListEventQuery;
import com.solvd.micro9.tickets.service.EsEventCommandHandler;
import com.solvd.micro9.tickets.service.EventQueryHandler;
import com.solvd.micro9.tickets.web.dto.EsDto;
import com.solvd.micro9.tickets.web.dto.EventDto;
import com.solvd.micro9.tickets.web.mapper.EsMapper;
import com.solvd.micro9.tickets.web.mapper.EventMapper;
import com.solvd.micro9.tickets.web.validation.CreateEventGroup;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/events")
@RequiredArgsConstructor
public class EventController {

    private final EsEventCommandHandler commandHandler;
    private final EventQueryHandler queryHandler;
    private final EventMapper eventMapper;
    private final EsMapper esMapper;

    @PostMapping
    public Mono<EsDto> create(@Validated(CreateEventGroup.class) @RequestBody EventDto eventDto) {
        Event event = eventMapper.dtoToDomain(eventDto);
        CreateEventCommand command = new CreateEventCommand(event, "Liza123");
        Mono<EsEvent> eventStoreMono = commandHandler.apply(command);
        return esMapper.domainToDto(eventStoreMono);
    }

    @GetMapping
    public Flux<EventDto> getAll() {
        Flux<Event> eventFlux = queryHandler.getAll();
        return eventMapper.domainToDto(eventFlux);
    }

    @GetMapping(value = "/user/{userId}")
    public Flux<EventDto> findByUserId(@PathVariable(name = "userId") Long userId) {
        ListEventQuery query = ListEventQuery.builder()
                .userId(userId)
                .build();
        Flux<Event> eventFlux = queryHandler.findByUserId(query);
        return eventMapper.domainToDto(eventFlux);
    }

}
