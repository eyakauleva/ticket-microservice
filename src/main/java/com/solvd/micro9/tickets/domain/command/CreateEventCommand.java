package com.solvd.micro9.tickets.domain.command;

import com.solvd.micro9.tickets.domain.EventCategory;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class CreateEventCommand {

    @TargetAggregateIdentifier
    private Long eventId;
    private String name;
    private String description;
    private EventCategory category;
    private LocalDateTime eventTime;
    private BigDecimal price;

}
