package com.solvd.micro9.tickets.web.controller;

import com.solvd.micro9.tickets.domain.Event;
import com.solvd.micro9.tickets.domain.command.CreateEventCommand;
import com.solvd.micro9.tickets.domain.event.EventStoreEvents;
import com.solvd.micro9.tickets.domain.query.ListEventQuery;
import com.solvd.micro9.tickets.service.EventService;
import com.solvd.micro9.tickets.web.dto.EventDto;
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

    private final EventService eventService;
    private final EventMapper eventMapper;

    @PostMapping
    public Mono<EventStoreEvents> create(@Validated(CreateEventGroup.class) @RequestBody EventDto eventDto) {
        Event event = eventMapper.dtoToDomain(eventDto);
        CreateEventCommand command = new CreateEventCommand(event, "Liza123");
        Mono<EventStoreEvents> eventStoreMono = eventService.create(command);
        return eventStoreMono; //TODO map to dto
    }

    @GetMapping
    public Flux<EventDto> getAll() {
        Flux<Event> eventFlux = eventService.getAll();
        return eventMapper.domainToDto(eventFlux);
    }

    @GetMapping(value = "/user/{userId}")
    public Flux<EventDto> findByUserId(@PathVariable(name = "userId") Long userId) {
        ListEventQuery query = ListEventQuery.builder()
                .userId(userId)
                .build();
        Flux<Event> eventFlux = eventService.findByUserId(query);
        return eventMapper.domainToDto(eventFlux);
    }

}
