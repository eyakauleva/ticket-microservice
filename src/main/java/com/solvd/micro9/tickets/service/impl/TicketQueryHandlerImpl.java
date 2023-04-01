package com.solvd.micro9.tickets.service.impl;

import com.solvd.micro9.tickets.domain.aggregate.Ticket;
import com.solvd.micro9.tickets.persistence.snapshot.TicketRepository;
import com.solvd.micro9.tickets.service.TicketQueryHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class TicketQueryHandlerImpl implements TicketQueryHandler {

    private final TicketRepository ticketRepository;

    @Override
    public Flux<Ticket> getAll() {
        return ticketRepository.findAll();
    }

}
