package com.solvd.micro9.tickets.persistence.eventstore;

import com.solvd.micro9.tickets.domain.es.EsTicket;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface EsTicketRepository extends ReactiveMongoRepository<EsTicket, Long> {
}