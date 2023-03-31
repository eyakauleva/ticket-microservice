package com.solvd.micro9.tickets.domain.es;

import lombok.*;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "es_tickets")
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class EsTicket extends Es {

    @Transient
    public static final String SEQUENCE_NAME = "event_store_tickets_sequence";

    @Builder
    public EsTicket(Long id,
                    EsEventType type,
                    LocalDateTime time,
                    String createdBy,
                    String entityId,
                    String payload) {
        super(id, type, time, createdBy, entityId, payload);
    }

}
