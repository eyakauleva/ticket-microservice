package com.solvd.micro9.tickets.domain.event;

import com.solvd.micro9.tickets.domain.EventCategory;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class EventCreatedEvent {

    private Long eventId;
    private String name;
    private String description;
    private EventCategory category;
    private LocalDateTime eventTime;
    private BigDecimal price;

}
