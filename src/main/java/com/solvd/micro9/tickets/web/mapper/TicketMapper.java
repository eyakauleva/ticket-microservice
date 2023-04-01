package com.solvd.micro9.tickets.web.mapper;

import com.solvd.micro9.tickets.domain.aggregate.Ticket;
import com.solvd.micro9.tickets.web.dto.EventDto;
import com.solvd.micro9.tickets.web.dto.TicketDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import reactor.core.publisher.Flux;

@Mapper(componentModel = "spring", uses = {EventMapper.class})
public interface TicketMapper {

    TicketDto domainToDto(Ticket ticket);

    default Flux<TicketDto> domainToDto(Flux<Ticket> ticketFlux) {
        return ticketFlux.map(this::domainToDto);
    }

    @Mapping(source = "event", target = "eventId", qualifiedByName = "getEventId")
    Ticket dtoToDomain(TicketDto ticketDto);

    @Named("getEventId")
    static String getEventId(EventDto event) {
        return event.getId();
    }

}
