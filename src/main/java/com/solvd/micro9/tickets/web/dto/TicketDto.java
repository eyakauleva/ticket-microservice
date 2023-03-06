package com.solvd.micro9.tickets.web.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class TicketDto {

    private Long id;

    @NotNull(message = "Ticket must refer to a client")
    private Long userId;

    @NotNull(message = "Ticket's event must be set")
    @Valid
    private EventDto event;

    @NotNull(message = "Ticket's quantity must be set")
    private Integer quantity;

    @NotNull(message = "Ticket's price must be set")
    private BigDecimal price;

}
