package com.solvd.micro9.tickets.web.dto;

import com.solvd.micro9.tickets.domain.EventCategory;
import com.solvd.micro9.tickets.web.validation.CreateEventGroup;
import com.solvd.micro9.tickets.web.validation.CreateTicketGroup;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class EventDto {

    @NotNull(groups = CreateTicketGroup.class, message = "Event's id must be set")
    private Long id;

    @NotNull(groups = CreateEventGroup.class, message = "Event's name must be set")
    private String name;

    @NotNull(groups = CreateEventGroup.class, message = "Event's description must be set")
    private String description;

    @NotNull(groups = CreateEventGroup.class, message = "Event's category must be set")
    private EventCategory category;

    @NotNull(groups = CreateEventGroup.class, message = "Event's date and time must be set")
    private LocalDateTime eventTime;

    @NotNull(groups = CreateEventGroup.class, message = "Event's price must be set")
    private BigDecimal price;

    public EventDto(Long id) {
        this.id = id;
    }

}
