package com.solvd.micro9.tickets.service.impl;

import com.google.gson.Gson;
import com.solvd.micro9.tickets.domain.aggregate.Ticket;
import com.solvd.micro9.tickets.domain.es.Es;
import com.solvd.micro9.tickets.persistence.snapshot.TicketRepository;
import com.solvd.micro9.tickets.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;

    @Override
    public Mono<Ticket> create(Es eventStore) {
        Ticket ticket = new Gson().fromJson(eventStore.getPayload(), Ticket.class);
        ticket.setId(eventStore.getEntityId());
        ticket.setNew(true);
        return ticketRepository.save(ticket);
    }

    @Override
    public Mono<Ticket> setUserToNull(Es eventStore) {
        return ticketRepository.findById(eventStore.getEntityId())
                .map(ticket -> {
                    ticket.setUserId(null);
                    ticketRepository.save(ticket).subscribe();
                    return ticket;
                });
    }

}
