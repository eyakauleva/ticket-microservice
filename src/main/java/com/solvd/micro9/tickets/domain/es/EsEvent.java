package com.solvd.micro9.tickets.domain.es;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "es_events")
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class EsEvent extends Es {

    @Transient
    public static final String SEQUENCE_NAME = "event_store_events_sequence";

    @Builder
    public EsEvent(Long id,
                   EsType type,
                   LocalDateTime time,
                   String createdBy,
                   String entityId,
                   String payload) {
        super(id, type, time, createdBy, entityId, payload);
    }

}
