package com.solvd.micro9.tickets.domain.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class EventStore {

    @Id
    private Long id;

    private EventType type;

    private LocalDateTime time;

    @Field(name = "created_by")
    private String createdBy;

    @Field(name = "entity_id")
    private String entityId;

    private String payload;

}
