package com.solvd.micro9.tickets.persistence;

import com.solvd.micro9.tickets.domain.Event;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends ReactiveMongoRepository<Event, Long> {
}
