package com.solvd.micro9.tickets.domain.event;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "event_store_events")
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class EventStoreEvents extends EventStore {

    @Transient
    public static final String SEQUENCE_NAME = "event_store_events_sequence";

    @Builder
    public EventStoreEvents(Long id,
                            EventType type,
                            LocalDateTime time,
                            String createdBy,
                            String entityId,
                            String payload) {
        super(id, type, time, createdBy, entityId, payload);
    }

}
