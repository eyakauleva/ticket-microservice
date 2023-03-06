package com.solvd.micro9.tickets.service.impl;

import com.solvd.micro9.tickets.domain.Ticket;
import com.solvd.micro9.tickets.persistence.TicketRepository;
import com.solvd.micro9.tickets.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;

    @Transactional
    public Ticket create(Ticket ticket){
        ticketRepository.save(ticket);
        return ticket;
    }

    @Override
    public List<Ticket> getAll() {
        return ticketRepository.findAll();
    }

}
