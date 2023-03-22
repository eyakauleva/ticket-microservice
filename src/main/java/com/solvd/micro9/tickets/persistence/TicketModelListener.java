package com.solvd.micro9.tickets.persistence;

import com.solvd.micro9.tickets.domain.Ticket;
import com.solvd.micro9.tickets.domain.exception.ServerException;
import com.solvd.micro9.tickets.service.SequenceGeneratorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.concurrent.ExecutionException;

@Component
@Slf4j
@RequiredArgsConstructor
public class TicketModelListener extends AbstractMongoEventListener<Ticket> {

    private final SequenceGeneratorService sequenceGenerator;

    @Override
    public void onBeforeConvert(BeforeConvertEvent<Ticket> ticket) {
        try {
            if (Objects.isNull(ticket.getSource().getId())) {
                ticket.getSource().setId(sequenceGenerator.generateSequence(Ticket.SEQUENCE_NAME));
            }
        } catch (InterruptedException | ExecutionException e) {
            log.error(e.getMessage());
            throw new ServerException(e);
        }
    }

}
