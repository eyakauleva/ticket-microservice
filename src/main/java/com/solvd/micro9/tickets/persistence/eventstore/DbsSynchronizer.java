package com.solvd.micro9.tickets.persistence.eventstore;

import com.solvd.micro9.tickets.domain.es.Es;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DbsSynchronizer {

    public void sync(Es event) {
        //TODO consider TICKETS_USER_ID_SET_TO_NULL_BY_USER_ID event and others ...
    }

}
