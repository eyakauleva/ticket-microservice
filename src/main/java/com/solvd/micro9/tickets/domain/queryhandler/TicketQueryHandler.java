package com.solvd.micro9.tickets.domain.queryhandler;

import com.solvd.micro9.tickets.domain.Ticket;
import com.solvd.micro9.tickets.domain.query.ListTicketQuery;
import com.solvd.micro9.tickets.persistence.TicketRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Slf4j
@Component
@RequiredArgsConstructor
public class TicketQueryHandler {

    private final TicketRepository ticketRepository;

    @QueryHandler
    public Flux<Ticket> handle(ListTicketQuery query) {
        log.info("Handling ListTicketQuery: {}", query);

        return ticketRepository.findAll();
    }

}
