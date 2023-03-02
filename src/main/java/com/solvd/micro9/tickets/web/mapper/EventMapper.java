package com.solvd.micro9.tickets.web.mapper;

import com.solvd.micro9.tickets.domain.Event;
import com.solvd.micro9.tickets.web.dto.EventDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EventMapper {

    EventDto domainToDto(Event event);

    List<EventDto> domainToDto(List<Event> events);

}
