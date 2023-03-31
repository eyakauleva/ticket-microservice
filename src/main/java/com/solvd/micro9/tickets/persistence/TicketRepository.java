package com.solvd.micro9.tickets.persistence;

import com.solvd.micro9.tickets.domain.es.EsTicket;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface TicketRepository extends ReactiveMongoRepository<EsTicket, Long> {
}