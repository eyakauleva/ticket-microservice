package com.solvd.micro9.tickets.domain.event;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class TicketCreatedEvent {

    private Long ticketId;
    private Long userId;
    private Long eventId;
    private Integer quantity;
    private BigDecimal price;

}
