package com.solvd.micro9.tickets.persistence;

import com.solvd.micro9.tickets.domain.Ticket;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.mongodb.repository.Update;
import org.springframework.data.repository.query.Param;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TicketRepository extends ReactiveMongoRepository<Ticket, Long> {

    @Override
    @Aggregation("{$lookup: {from: \"events\", localField: \"event_id\", foreignField: \"_id\", as: \"event\",},}")
    Flux<Ticket> findAll();

    @Query("{'userId' : :#{#userId}}")
    Flux<Ticket> findByUserId(@Param("userId") Long userId);

    @Query("{'userId' : :#{#userId}}")
    @Update("{$set: {'userId': null}}")
    Mono<Void> updateDeletedUserTickets(@Param("userId") Long userId);

}