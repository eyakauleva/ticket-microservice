package com.solvd.micro9.tickets.service.impl;

import com.google.gson.Gson;
import com.solvd.micro9.tickets.domain.Ticket;
import com.solvd.micro9.tickets.persistence.TicketRepository;
import com.solvd.micro9.tickets.service.EsTicketQueryHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class EsTicketQueryHandlerImpl implements EsTicketQueryHandler {

    private final TicketRepository ticketRepository;

    @Override
    public Flux<Ticket> getAll() { //TODO consider TICKETS_USER_ID_SET_TO_NULL_BY_USER_ID event
        return ticketRepository.findAll()
                .map(ticketStore -> {
                    Ticket ticket = new Gson().fromJson(ticketStore.getPayload(), Ticket.class);
                    ticket.setId(ticketStore.getEntityId());
                    return ticket;
                });
    }

}
