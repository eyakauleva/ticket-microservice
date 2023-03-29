package com.solvd.micro9.tickets.domain.eventhandler;

import com.solvd.micro9.tickets.domain.Ticket;
import com.solvd.micro9.tickets.domain.event.DeletedUsersTicketsUpdatedEvent;
import com.solvd.micro9.tickets.domain.event.TicketCreatedEvent;
import com.solvd.micro9.tickets.persistence.TicketRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class TicketProjectionEventHandler {

    private final TicketRepository ticketRepository;

    @EventHandler
    public void ticketCreatedEventHandler(TicketCreatedEvent event) {
        log.info("Applying TicketCreatedEvent: {}", event);

        Ticket ticketView = new Ticket(
                event.getTicketId(),
                event.getUserId(),
                event.getEventId(),
                event.getQuantity(),
                event.getPrice()
        );
        ticketRepository.save(ticketView);
    }

    @EventHandler
    public void deletedUsersTicketsUpdatedEventHandler(DeletedUsersTicketsUpdatedEvent event) {
        log.info("Applying DeletedUsersTicketsUpdatedEvent: {}", event);

        ticketRepository.updateDeletedUserTickets(event.getUserId());
    }

}
