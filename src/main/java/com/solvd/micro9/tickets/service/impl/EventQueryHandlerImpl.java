package com.solvd.micro9.tickets.service.impl;

import com.solvd.micro9.tickets.domain.aggregate.Event;
import com.solvd.micro9.tickets.domain.aggregate.Ticket;
import com.solvd.micro9.tickets.domain.query.ListEventQuery;
import com.solvd.micro9.tickets.persistence.snapshot.EventRepository;
import com.solvd.micro9.tickets.persistence.snapshot.TicketRepository;
import com.solvd.micro9.tickets.service.EventQueryHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class EventQueryHandlerImpl implements EventQueryHandler {

    private final EventRepository eventRepository;
    private final TicketRepository ticketRepository;

    @Override
    public Flux<Event> getAll() {
        return eventRepository.findAll();
    }

    @Override
    public Flux<Event> findByUserId(ListEventQuery query) {
        return ticketRepository.findAll()
                .filter(ticket -> query.getUserId().equals(ticket.getUserId()))
                .distinct(Ticket::getEventId)
                .flatMap(ticket -> eventRepository.findById(ticket.getEventId()));
    }

}
