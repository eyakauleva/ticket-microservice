package com.solvd.micro9.tickets.persistence.eventstore;

import com.solvd.micro9.tickets.domain.es.EsEvent;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.Param;
import reactor.core.publisher.Flux;

public interface EsEventRepository extends ReactiveMongoRepository<EsEvent, Long> {

    @Query("{'entityId' : :#{#entityId}}")
    Flux<EsEvent> findByEntityId(@Param("entityId") String entityId);

}
