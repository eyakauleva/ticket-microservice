package com.solvd.micro9.tickets.persistence;

import com.solvd.micro9.tickets.domain.Ticket;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface TicketRepository extends ReactiveMongoRepository<Ticket, Long> {

    Flux<Ticket> findByUserId(@Param("userId") Long userId);

    @Query(value = "update tickets set user_id=null where user_id=:userId")
    Mono<Void> updateDeletedUserTickets(@Param("userId") Long userId);

}
