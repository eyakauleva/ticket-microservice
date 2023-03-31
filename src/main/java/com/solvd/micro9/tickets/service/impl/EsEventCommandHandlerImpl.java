package com.solvd.micro9.tickets.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.solvd.micro9.tickets.domain.command.CreateEventCommand;
import com.solvd.micro9.tickets.domain.es.EsEvent;
import com.solvd.micro9.tickets.domain.es.EsEventType;
import com.solvd.micro9.tickets.persistence.EventRepository;
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

    private final EventRepository eventRepository;

    @SneakyThrows
    @Override
    public Mono<EsEvent> apply(CreateEventCommand command) {
        ObjectMapper mapper = new ObjectMapper();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        mapper.registerModule(new JavaTimeModule());
        mapper.setDateFormat(dateFormat);
        String payload = mapper.writeValueAsString(command.getEvent());


        EsEvent event = EsEvent.builder()
                .type(EsEventType.EVENT_CREATED)
                .time(LocalDateTime.now())
                .createdBy(command.getCommandBy())
                .entityId(UUID.randomUUID().toString())
                .payload(payload)
                .build();
        return eventRepository.save(event);
    }

}
