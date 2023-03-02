package com.solvd.micro9.tickets.web.dto;

import com.solvd.micro9.tickets.domain.Event;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class TicketDto {

    private Long id;
    private Long userId;
    private Event event;
    private Integer quantity;
    private BigDecimal price;

}
