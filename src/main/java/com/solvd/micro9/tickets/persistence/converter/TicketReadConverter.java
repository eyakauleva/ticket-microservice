package com.solvd.micro9.tickets.persistence.converter;

import com.solvd.micro9.tickets.domain.Event;
import com.solvd.micro9.tickets.domain.EventCategory;
import com.solvd.micro9.tickets.domain.Ticket;
import org.bson.Document;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;

@ReadingConverter
public class TicketReadConverter implements Converter<Document, Ticket> {

    @Override
    public Ticket convert(Document source) {
        if(Objects.nonNull(source.get("event"))){
            Document eventDoc = (Document) ((ArrayList<?>) source.get("event")).get(0);
            return new Ticket(
                    source.getLong("_id"),
                    source.getLong("user_id"),
                    source.getLong("event_id"),
                    new Event(
                            eventDoc.getLong("_id"),
                            eventDoc.getString("name"),
                            eventDoc.getString("description"),
                            EventCategory.valueOf(eventDoc.getString("category")),
                            eventDoc.get("eventTime", LocalDateTime.class),
                            new BigDecimal(eventDoc.getString("price"))
                    ),
                    source.getInteger("quantity"),
                    new BigDecimal(source.getString("price")));
        } else {
            return new Ticket(
                    source.getLong("_id"),
                    source.getLong("user_id"),
                    source.getLong("event_id"),
                    source.getInteger("quantity"),
                    new BigDecimal(source.getString("price")));
        }
    }

}
