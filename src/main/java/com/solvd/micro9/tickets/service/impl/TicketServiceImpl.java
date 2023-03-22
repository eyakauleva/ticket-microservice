package com.solvd.micro9.tickets.service.impl;

import com.solvd.micro9.tickets.domain.Ticket;
import com.solvd.micro9.tickets.domain.exception.ResourceDoesNotExistException;
import com.solvd.micro9.tickets.persistence.EventRepository;
import com.solvd.micro9.tickets.persistence.TicketRepository;
import com.solvd.micro9.tickets.service.TicketService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Slf4j
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;
    private final EventRepository eventRepository;

    @Transactional
    public Mono<Ticket> create(Ticket ticket) {
        return eventRepository.findById(ticket.getEvent().getId())
                .switchIfEmpty(Mono.error(new ResourceDoesNotExistException("Event [id=" + ticket.getEvent().getId() + "] does not exist")))
                .zipWith(ticketRepository.save(ticket))
                .map(result -> {
                    result.getT2().setEvent(result.getT1());
                    return result.getT2();
                });
    }

    @Override
    public Flux<Ticket> getAll() {
        Flux<Ticket> ticketFlux = ticketRepository.findAll();
        log.info("ticket:{}",ticketFlux);
        return ticketFlux;
    }

    @Override
    public void updateDeletedUserTickets(Long userId) {
        ticketRepository.updateDeletedUserTickets(userId)
                .subscribe();
    }

}
