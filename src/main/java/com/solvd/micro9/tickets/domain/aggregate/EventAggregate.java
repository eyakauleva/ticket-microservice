package com.solvd.micro9.tickets.domain.aggregate;

import com.solvd.micro9.tickets.domain.EventCategory;
import com.solvd.micro9.tickets.domain.command.CreateEventCommand;
import com.solvd.micro9.tickets.domain.event.EventCreatedEvent;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Aggregate
@Slf4j
@NoArgsConstructor
public class EventAggregate {

    @AggregateIdentifier
    private Long eventId;
    private String name;
    private String description;
    private EventCategory category;
    private LocalDateTime eventTime;
    private BigDecimal price;

    @CommandHandler
    public EventAggregate(CreateEventCommand command) {
        log.info("Handling CreateEventCommand: {}", command);

        AggregateLifecycle.apply(
                new EventCreatedEvent(
                        command.getEventId(),
                        command.getName(),
                        command.getDescription(),
                        command.getCategory(),
                        command.getEventTime(),
                        command.getPrice()
                )
        );
    }

    @EventSourcingHandler
    public void on(EventCreatedEvent event) {
        log.info("Applying EventCreatedEvent: {}", event);

        eventId = event.getEventId();
        name = event.getName();
        description = event.getDescription();
        category = event.getCategory();
        eventTime = event.getEventTime();
        price = event.getPrice();
    }

}
