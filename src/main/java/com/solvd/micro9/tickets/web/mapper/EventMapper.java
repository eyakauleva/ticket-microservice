package com.solvd.micro9.tickets.web.mapper;

import com.solvd.micro9.tickets.domain.Event;
import com.solvd.micro9.tickets.web.dto.EventDto;
import org.mapstruct.Mapper;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Mapper(componentModel = "spring")
public interface EventMapper {

    EventDto domainToDto(Event event);

    default Mono<EventDto> domainToDto(Mono<Event> eventMono) {
        return eventMono.map(this::domainToDto);
    }

    default Flux<EventDto> domainToDto(Flux<Event> eventFlux) {
        return eventFlux.map(this::domainToDto);
    }

    Event dtoToDomain(EventDto eventDto);

}
