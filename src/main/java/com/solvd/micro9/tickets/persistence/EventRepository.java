package com.solvd.micro9.tickets.persistence;

import com.solvd.micro9.tickets.domain.es.EsEvent;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface EventRepository extends ReactiveMongoRepository<EsEvent, Long> {

    @Query("{'entityId' : :#{#entityId}}")
    Flux<EsEvent> findByEntityId(@Param("entityId") String entityId);

}
