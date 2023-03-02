package com.solvd.micro9.tickets.service;

import com.solvd.micro9.tickets.domain.Event;
import com.solvd.micro9.tickets.domain.Ticket;
import com.solvd.micro9.tickets.persistence.EventRepository;
import com.solvd.micro9.tickets.persistence.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;
    private final TicketRepository ticketRepository;

    public List<Event> getAll() {
        return eventRepository.findAll();
    }

    public List<Event> findByUserId(Long userId) {
        List<Ticket> userTickets = ticketRepository.findByUserId(userId);
        return userTickets.stream()
                .map(ticket -> eventRepository.findById(ticket.getId()).get())
                .collect(Collectors.toList());
    }

}
