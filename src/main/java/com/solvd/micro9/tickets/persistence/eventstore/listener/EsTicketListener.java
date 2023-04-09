package com.solvd.micro9.tickets.persistence.eventstore.listener;

import com.solvd.micro9.tickets.domain.es.EsTicket;
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
public class EsTicketListener extends AbstractMongoEventListener<EsTicket> {

    private final SequenceGeneratorService sequenceGenerator;

    @Override
    public void onBeforeConvert(BeforeConvertEvent<EsTicket> event) {
        try {
            if (Objects.isNull(event.getSource().getId())) {
                event.getSource().setId(sequenceGenerator.generateSequence(EsTicket.SEQUENCE_NAME));
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new ServerException(e);
        }
    }

}
