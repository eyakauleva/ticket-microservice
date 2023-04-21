package com.solvd.micro9.tickets.service.impl;

import com.solvd.micro9.tickets.domain.es.Es;
import com.solvd.micro9.tickets.service.DbsSynchronizer;
import com.solvd.micro9.tickets.service.EventService;
import com.solvd.micro9.tickets.service.TicketService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.scheduler.Schedulers;

@Service
@RequiredArgsConstructor
@Slf4j
public class DbsSynchronizerImpl implements DbsSynchronizer {

    private final EventService eventService;
    private final TicketService ticketService;

    @SneakyThrows
    @Override
    public void sync(Es eventStore) {
        switch (eventStore.getType()) {
            case EVENT_CREATED: {
                eventService.create(eventStore)
                        .subscribeOn(Schedulers.boundedElastic())
                        .subscribe();
                break;
            }
            case TICKET_CREATED: {
                ticketService.create(eventStore)
                        .subscribeOn(Schedulers.boundedElastic())
                        .subscribe();
                break;
            }
            case TICKET_USER_DELETED: {
                ticketService.setUserToNull(eventStore)
                        .subscribeOn(Schedulers.boundedElastic())
                        .subscribe();
                break;
            }
        }
    }

}