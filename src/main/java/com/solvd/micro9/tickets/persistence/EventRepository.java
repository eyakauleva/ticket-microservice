package com.solvd.micro9.tickets.persistence;

import com.solvd.micro9.tickets.domain.event.EventStoreEvents;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface EventRepository extends ReactiveMongoRepository<EventStoreEvents, Long> {

    @Query("{'entityId' : :#{#entityId}}")
    Mono<EventStoreEvents> findByEntityId(@Param("entityId") Long entityId);

}
