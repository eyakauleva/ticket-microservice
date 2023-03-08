package com.solvd.micro9.tickets.persistence;

import com.solvd.micro9.tickets.domain.Ticket;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface TicketRepository extends R2dbcRepository<Ticket, Long> {

    @Query(value = "select * from tickets t where t.user_id=:userId")
    Flux<Ticket> findByUserId(@Param("userId") Long userId);

}
