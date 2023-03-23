package com.solvd.micro9.tickets.service.impl;

import com.solvd.micro9.tickets.domain.Event;
import com.solvd.micro9.tickets.domain.Ticket;
import com.solvd.micro9.tickets.persistence.EventRepository;
import com.solvd.micro9.tickets.persistence.TicketRepository;
import com.solvd.micro9.tickets.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final TicketRepository ticketRepository;

    public Flux<Event> getAll() {
        return eventRepository.findAll();
    }

    public Flux<Event> findByUserId(Long userId) {
        Flux<Ticket> ticketFlux = ticketRepository.findByUserId(userId);
        return ticketFlux.flatMap(ticket -> eventRepository.findById(ticket.getEventId()))
                .distinct(Event::getId);
    }

    public Mono<Event> create(Event event) {
        return eventRepository.save(event);
    }

}
