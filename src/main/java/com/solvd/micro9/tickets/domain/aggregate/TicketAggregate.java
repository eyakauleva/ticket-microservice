package com.solvd.micro9.tickets.domain.aggregate;

import com.solvd.micro9.tickets.domain.Event;
import com.solvd.micro9.tickets.domain.command.CreateTicketCommand;
import com.solvd.micro9.tickets.domain.command.UpdateDeletedUserTicketsCommand;
import com.solvd.micro9.tickets.domain.event.DeletedUsersTicketsUpdatedEvent;
import com.solvd.micro9.tickets.domain.event.TicketCreatedEvent;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.math.BigDecimal;

@Aggregate
@Slf4j
@NoArgsConstructor
public class TicketAggregate {

    @AggregateIdentifier
    private Long ticketId;
    private Long userId;
    private Long eventId;
    private Integer quantity;
    private BigDecimal price;

    @CommandHandler
    public TicketAggregate(CreateTicketCommand command) {
        log.info("Handling CreateEventCommand: {}", command);

        AggregateLifecycle.apply(
                new TicketCreatedEvent(
                        command.getTicketId(),
                        command.getUserId(),
                        command.getEventId(),
                        command.getQuantity(),
                        command.getPrice()
                )
        );
    }

    @CommandHandler
    public void handle(UpdateDeletedUserTicketsCommand command){
        log.info("Handling UpdateDeletedUserTicketsCommand: {}", command);

        AggregateLifecycle.apply(new DeletedUsersTicketsUpdatedEvent(command.getUserId()));
    }

    @EventSourcingHandler
    public void on(TicketCreatedEvent event) {
        log.info("Applying TicketCreatedEvent: {}", event);

        ticketId = event.getTicketId();
        userId = event.getUserId();
        eventId = event.getEventId();
        quantity = event.getQuantity();
        price = event.getPrice();
    }

    @EventSourcingHandler
    public void on(DeletedUsersTicketsUpdatedEvent event) {
        log.info("Applying DeletedUsersTicketsUpdatedEvent: {}", event);

        userId = event.getUserId();
    }

}
