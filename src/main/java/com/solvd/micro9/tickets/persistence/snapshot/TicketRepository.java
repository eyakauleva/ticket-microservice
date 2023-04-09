package com.solvd.micro9.tickets.persistence.snapshot;

import com.solvd.micro9.tickets.domain.aggregate.Ticket;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.repository.query.Param;
import reactor.core.publisher.Flux;

public interface TicketRepository extends R2dbcRepository<Ticket, String> {

    @Query(value = "select t.id, t.user_id, t.event_id, t.quantity, t.price from tickets t where t.user_id=:userId")
    Flux<Ticket> findByUserId(@Param("userId") String userId);

}
