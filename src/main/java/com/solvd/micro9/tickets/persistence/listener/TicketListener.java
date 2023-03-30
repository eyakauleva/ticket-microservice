package com.solvd.micro9.tickets.persistence.listener;

import com.solvd.micro9.tickets.domain.event.EventStoreTickets;
import com.solvd.micro9.tickets.domain.exception.ServerException;
import com.solvd.micro9.tickets.service.SequenceGeneratorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.concurrent.ExecutionException;

@Component
@RequiredArgsConstructor
public class TicketListener extends AbstractMongoEventListener<EventStoreTickets> {

    private final SequenceGeneratorService sequenceGenerator;

    @Override
    public void onBeforeConvert(BeforeConvertEvent<EventStoreTickets> event) {
        try {
            if (Objects.isNull(event.getSource().getId())) {
                event.getSource().setId(sequenceGenerator.generateSequence(EventStoreTickets.SEQUENCE_NAME));
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new ServerException(e);
        }
    }

}
