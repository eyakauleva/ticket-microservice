package com.solvd.micro9.tickets.persistence;

import com.solvd.micro9.tickets.domain.event.EventStoreTickets;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface TicketRepository extends ReactiveMongoRepository<EventStoreTickets, Long> {
}