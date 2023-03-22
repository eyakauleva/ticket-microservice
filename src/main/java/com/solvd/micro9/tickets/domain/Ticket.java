package com.solvd.micro9.tickets.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;

@Document(collection = "tickets")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {

    @Transient
    public static final String SEQUENCE_NAME = "ticket_sequence";

    @Id
    private Long id;

    @Indexed
    @Field(name = "user_id")
    private Long userId;

//    @Field("event_id")
//    @DocumentReference
//    private Long eventId;

    //@Transient
//    @DocumentReference(lookup = "{'id' : ?#{id}, 'name' : ?#{name} }")
    @DocumentReference(lookup = "{'id' : ?#{self._id}, 'name' : ?#{self._name} }")
    private Event event;

    private Integer quantity;

    private BigDecimal price;

}
