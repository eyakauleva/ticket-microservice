package com.solvd.micro9.tickets.persistence;

import com.solvd.micro9.tickets.domain.Event;
import com.solvd.micro9.tickets.domain.exception.ServerException;
import com.solvd.micro9.tickets.service.SequenceGeneratorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;

@Component
@Slf4j
@RequiredArgsConstructor
public class TicketModelListener extends AbstractMongoEventListener<Event> {

    private final SequenceGeneratorService sequenceGenerator;

    @Override
    public void onBeforeConvert(BeforeConvertEvent<Event> event) {
        try {
            event.getSource().setId(sequenceGenerator.generateSequence(Event.SEQUENCE_NAME));
        } catch (InterruptedException | ExecutionException e) {
            log.error(e.getMessage());
            throw new ServerException(e);
        }
    }

}

