package com.solvd.micro9.tickets.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "events")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Event {

    @Transient
    public static final String SEQUENCE_NAME = "event_sequence";

    @Id
    private Long id;

    private String name;

    private String description;

    private EventCategory category;

    @Field(name = "event_time")
    private LocalDateTime eventTime;

    private BigDecimal price;

    @DocumentReference
    private List<Ticket> tickets;

}
