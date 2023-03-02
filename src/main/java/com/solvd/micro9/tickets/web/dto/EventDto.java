package com.solvd.micro9.tickets.web.dto;

import com.solvd.micro9.tickets.domain.TicketCategory;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class EventDto {

    private Long id;
    private String name;
    private String description;
    private TicketCategory category;
    private LocalDateTime eventTime;
    private BigDecimal price;

}
