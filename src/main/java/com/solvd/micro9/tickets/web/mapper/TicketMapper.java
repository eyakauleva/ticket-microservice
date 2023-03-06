package com.solvd.micro9.tickets.web.mapper;

import com.solvd.micro9.tickets.domain.Ticket;
import com.solvd.micro9.tickets.web.dto.TicketDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {EventMapper.class})
public interface TicketMapper {

    TicketDto domainToDto(Ticket ticket);

    List<TicketDto> domainToDto(List<Ticket> tickets);

    Ticket dtoToDomain(TicketDto ticketDto);

}
