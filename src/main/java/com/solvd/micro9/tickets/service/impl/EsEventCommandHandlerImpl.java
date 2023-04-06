package com.solvd.micro9.tickets.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.solvd.micro9.tickets.domain.command.CreateEventCommand;
import com.solvd.micro9.tickets.domain.es.EsEvent;
import com.solvd.micro9.tickets.domain.es.EsType;
import com.solvd.micro9.tickets.messaging.KfProducer;
import com.solvd.micro9.tickets.persistence.eventstore.EsEventRepository;
import com.solvd.micro9.tickets.service.EsEventCommandHandler;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EsEventCommandHandlerImpl implements EsEventCommandHandler {

    private final EsEventRepository esEventRepository;
    private final KfProducer producer;

    @SneakyThrows
    @Override
    public Mono<EsEvent> apply(CreateEventCommand command) {
        ObjectMapper mapper = new ObjectMapper();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        mapper.registerModule(new JavaTimeModule());
        mapper.setDateFormat(dateFormat);
        String payload = mapper.writeValueAsString(command.getEvent());
        EsEvent event = EsEvent.builder()
                .type(EsType.EVENT_CREATED)
                .time(LocalDateTime.now())
                .createdBy(command.getCommandBy())
                .entityId(UUID.randomUUID().toString())
                .payload(payload)
                .build();
        return esEventRepository.save(event)
                .doOnSuccess(esEvent -> producer.send("New event", esEvent));
    }

}
