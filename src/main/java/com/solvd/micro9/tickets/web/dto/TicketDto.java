package com.solvd.micro9.tickets.web.dto;

import com.solvd.micro9.tickets.web.validation.CreateTicketGroup;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class TicketDto {

    private String id;

    @NotNull(groups = CreateTicketGroup.class, message = "Ticket must refer to a client")
    private String userId;

    @NotNull(groups = CreateTicketGroup.class, message = "Ticket's event must be set")
    @Valid
    private EventDto event;

    @NotNull(groups = CreateTicketGroup.class, message = "Ticket's quantity must be set")
    private Integer quantity;

    @NotNull(groups = CreateTicketGroup.class, message = "Ticket's price must be set")
    private BigDecimal price;

}
