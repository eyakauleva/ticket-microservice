package com.solvd.micro9.tickets.persistence;

import com.solvd.micro9.tickets.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
}
