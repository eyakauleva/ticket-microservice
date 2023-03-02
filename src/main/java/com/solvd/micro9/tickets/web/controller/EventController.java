package com.solvd.micro9.tickets.web.controller;

import com.solvd.micro9.tickets.domain.Event;
import com.solvd.micro9.tickets.service.EventService;
import com.solvd.micro9.tickets.web.mapper.EventMapper;
import com.solvd.micro9.tickets.web.dto.EventDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/events")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;
    private final EventMapper eventMapper;

    @GetMapping
    public List<EventDto> getAll() {
        List<Event> events = eventService.getAll();
        return eventMapper.domainToDto(events);
    }

    @GetMapping(value = "/{userId}")
    public List<EventDto> findByUserId(@PathVariable(name = "userId") Long userId) {
        List<Event> events = eventService.findByUserId(userId);
        return eventMapper.domainToDto(events);
    }

}
