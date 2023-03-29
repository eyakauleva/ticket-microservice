package com.solvd.micro9.tickets.domain.command;

import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.math.BigDecimal;

@Data
public class CreateTicketCommand {

    @TargetAggregateIdentifier
    private Long ticketId;
    private Long userId;
    private Long eventId;
    private Integer quantity;
    private BigDecimal price;

}
