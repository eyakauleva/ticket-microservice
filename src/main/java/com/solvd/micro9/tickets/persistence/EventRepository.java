package com.solvd.micro9.tickets.persistence;

import com.solvd.micro9.tickets.domain.Event;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends R2dbcRepository<Event, Long> {
}
