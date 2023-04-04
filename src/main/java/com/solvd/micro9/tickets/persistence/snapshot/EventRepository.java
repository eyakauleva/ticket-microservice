package com.solvd.micro9.tickets.persistence.snapshot;

import com.solvd.micro9.tickets.domain.aggregate.Event;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface EventRepository extends R2dbcRepository<Event, String> {
}
