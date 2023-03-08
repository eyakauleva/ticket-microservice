package com.solvd.micro9.tickets.web.mapper;

import com.solvd.micro9.tickets.domain.Ticket;
import com.solvd.micro9.tickets.web.dto.TicketDto;
import org.mapstruct.Mapper;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Mapper(componentModel = "spring", uses = {EventMapper.class})
public interface TicketMapper {

    TicketDto domainToDto(Ticket ticket);

    default Mono<TicketDto> domainToDto(Mono<Ticket> ticketMono) {
        return ticketMono.map(this::domainToDto);
    }

    default Flux<TicketDto> domainToDto(Flux<Ticket> ticketFlux) {
        return ticketFlux.map(this::domainToDto);
    }

    Ticket dtoToDomain(TicketDto ticketDto);

}
