package com.solvd.micro9.tickets.persistence;

import com.solvd.micro9.tickets.domain.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    @Query(value = "select * from tickets t where t.user_id=:userId", nativeQuery = true)
    List<Ticket> findByUserId(@Param("userId") Long userId);

}
