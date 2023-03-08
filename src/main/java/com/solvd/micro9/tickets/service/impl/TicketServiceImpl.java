package com.solvd.micro9.tickets.service.impl;

import com.solvd.micro9.tickets.domain.Ticket;
import com.solvd.micro9.tickets.persistence.TicketRepository;
import com.solvd.micro9.tickets.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;

    @Transactional
    public Mono<Ticket> create(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    @Override
    public Flux<Ticket> getAll() {
        return ticketRepository.findAll();
    }

}
