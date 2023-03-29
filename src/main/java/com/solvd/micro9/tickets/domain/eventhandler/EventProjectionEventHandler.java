package com.solvd.micro9.tickets.domain.eventhandler;

import com.solvd.micro9.tickets.domain.Event;
import com.solvd.micro9.tickets.domain.event.EventCreatedEvent;
import com.solvd.micro9.tickets.persistence.EventRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class EventProjectionEventHandler {

    private final EventRepository eventRepository;

    @EventHandler
    public void eventCreatedEventHandler(EventCreatedEvent event){
        log.info("Applying EventCreatedEvent: {}", event);

        Event eventView = new Event(
                event.getEventId(),
                event.getName(),
                event.getDescription(),
                event.getCategory(),
                event.getEventTime(),
                event.getPrice()
        );
        eventRepository.save(eventView);
    }

}
