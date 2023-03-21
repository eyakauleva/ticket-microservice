package com.solvd.micro9.tickets.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Event {

    @Id
    private Long id;
    private String name;
    private String description;
    private TicketCategory category;
    private LocalDateTime eventTime;
    private BigDecimal price;

}
