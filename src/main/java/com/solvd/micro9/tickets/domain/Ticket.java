package com.solvd.micro9.tickets.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {

    private String id;
    private Long userId;
    private Long eventId;
    private Event event;
    private Integer quantity;
    private BigDecimal price;

}
