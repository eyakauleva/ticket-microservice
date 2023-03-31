package com.solvd.micro9.tickets.service.impl;

import com.google.gson.Gson;
import com.solvd.micro9.tickets.domain.Event;
import com.solvd.micro9.tickets.domain.Ticket;
import com.solvd.micro9.tickets.domain.query.ListEventQuery;
import com.solvd.micro9.tickets.persistence.EventRepository;
import com.solvd.micro9.tickets.persistence.TicketRepository;
import com.solvd.micro9.tickets.service.EsEventQueryHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class EsEventQueryHandlerImpl implements EsEventQueryHandler {

    private final EventRepository eventRepository;
    private final TicketRepository ticketRepository;

    @Override
    public Flux<Event> getAll() {
        return eventRepository.findAll()
                .map(eventStore -> {
                    Event event = new Gson().fromJson(eventStore.getPayload(), Event.class);
                    event.setId(eventStore.getEntityId());
                    return event;
                });
    }

    @Override
    public Flux<Event> findByUserId(ListEventQuery query) {
        return ticketRepository.findAll()
                .map(ticketStore -> new Gson().fromJson(ticketStore.getPayload(), Ticket.class))
                .filter(ticket -> query.getUserId().equals(ticket.getUserId()))
                .distinct(Ticket::getEventId)
                .flatMap(ticket -> eventRepository.findByEntityId(ticket.getEventId()))
                .map(eventStore -> {
                    Event event = new Gson().fromJson(eventStore.getPayload(), Event.class);
                    event.setId(eventStore.getEntityId());
                    return event;
                });
    }

}
