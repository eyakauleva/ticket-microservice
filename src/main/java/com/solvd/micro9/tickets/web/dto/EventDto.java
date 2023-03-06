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

    private String name;

    private String description;

    private TicketCategory category;

    private LocalDateTime eventTime;

    private BigDecimal price;

}
