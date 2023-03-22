package com.solvd.micro9.tickets.web.mapper;

import com.solvd.micro9.tickets.domain.Ticket;
import com.solvd.micro9.tickets.web.dto.EventDto;
import com.solvd.micro9.tickets.web.dto.TicketDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Mapper(componentModel = "spring", uses = {EventMapper.class})
public interface TicketMapper {

    //@Mapping(source = "eventId", target = "event", qualifiedByName = "setEvent")
    TicketDto domainToDto(Ticket ticket);

    default Mono<TicketDto> domainToDto(Mono<Ticket> ticketMono) {
        return ticketMono.map(this::domainToDto);
    }

    default Flux<TicketDto> domainToDto(Flux<Ticket> ticketFlux) {
        return ticketFlux.map(this::domainToDto);
    }

    //@Mapping(source = "event", target = "eventId", qualifiedByName = "getEventId")
    Ticket dtoToDomain(TicketDto ticketDto);

    @Named("getEventId")
    static Long getEventId(EventDto event) {
        return event.getId();
    }

    @Named("setEvent")
    static EventDto getEventId(Long eventId) {
        return new EventDto(eventId);
    }

}
