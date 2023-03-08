package com.solvd.micro9.tickets.web.controller;

import com.solvd.micro9.tickets.domain.Event;
import com.solvd.micro9.tickets.service.EventService;
import com.solvd.micro9.tickets.web.dto.EventDto;
import com.solvd.micro9.tickets.web.mapper.EventMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api/v1/events")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;
    private final EventMapper eventMapper;

    @GetMapping
    public Flux<EventDto> getAll() {
        Flux<Event> eventFlux = eventService.getAll();
        return eventMapper.domainToDto(eventFlux);
    }

    @GetMapping(value = "/user/{userId}")
    public Flux<EventDto> findByUserId(@PathVariable(name = "userId") Long userId) {
        Flux<Event> eventFlux = eventService.findByUserId(userId);
        return eventMapper.domainToDto(eventFlux);
    }

}
