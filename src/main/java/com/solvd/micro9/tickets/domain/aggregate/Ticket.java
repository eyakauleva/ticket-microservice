package com.solvd.micro9.tickets.domain.aggregate;

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
    private String eventId;
    private Integer quantity;
    private BigDecimal price;

}
