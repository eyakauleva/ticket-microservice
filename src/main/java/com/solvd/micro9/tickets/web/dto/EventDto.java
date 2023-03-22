package com.solvd.micro9.tickets.web.dto;

import com.solvd.micro9.tickets.domain.TicketCategory;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class EventDto {

    @NotNull(message = "Event's id must be set")
    private Long id;

    @NotNull(message = "Event's name must be set")
    private String name;

    @NotNull(message = "Event's description must be set")
    private String description;

    @NotNull(message = "Event's category must be set")
    private TicketCategory category;

    @NotNull(message = "Event's date and time must be set")
    private LocalDateTime eventTime;

    @NotNull(message = "Event's price must be set")
    private BigDecimal price;

}
