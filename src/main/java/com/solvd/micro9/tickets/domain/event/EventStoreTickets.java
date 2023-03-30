package com.solvd.micro9.tickets.domain.event;

import lombok.*;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "event_store_tickets")
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class EventStoreTickets extends EventStore {

    @Transient
    public static final String SEQUENCE_NAME = "event_store_tickets_sequence";

    @Builder
    public EventStoreTickets(Long id,
                             EventType type,
                             LocalDateTime time,
                             String createdBy,
                             String entityId,
                             String payload) {
        super(id, type, time, createdBy, entityId, payload);
    }

}
