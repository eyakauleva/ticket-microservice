package com.solvd.micro9.tickets.web.controller;

import com.solvd.micro9.tickets.domain.aggregate.Ticket;
import com.solvd.micro9.tickets.domain.command.CreateTicketCommand;
import com.solvd.micro9.tickets.domain.command.ProcessTicketUpdateCommand;
import com.solvd.micro9.tickets.domain.es.EsStatus;
import com.solvd.micro9.tickets.domain.es.EsTicket;
import com.solvd.micro9.tickets.service.EsTicketCommandHandler;
import com.solvd.micro9.tickets.service.TicketQueryHandler;
import com.solvd.micro9.tickets.web.dto.EsDto;
import com.solvd.micro9.tickets.web.dto.TicketDto;
import com.solvd.micro9.tickets.web.mapper.EsMapper;
import com.solvd.micro9.tickets.web.mapper.TicketMapper;
import com.solvd.micro9.tickets.web.validation.CreateTicketGroup;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/tickets")
@RequiredArgsConstructor
public class TicketController {

    private final EsTicketCommandHandler commandHandler;
    private final TicketQueryHandler queryHandler;
    private final TicketMapper ticketMapper;
    private final EsMapper esMapper;

    @PostMapping
    public Mono<EsDto> create(@Validated(CreateTicketGroup.class) @RequestBody TicketDto ticketDto) {
        Ticket ticket = ticketMapper.dtoToDomain(ticketDto);
        CreateTicketCommand command = new CreateTicketCommand(ticket, "Liza123");
        Mono<EsTicket> eventStoreMono = commandHandler.apply(command);
        return esMapper.domainToDto(eventStoreMono);
    }

    @GetMapping
    public Flux<TicketDto> getAll() {
        Flux<Ticket> tickets = queryHandler.getAll();
        return ticketMapper.domainToDto(tickets);
    }

    @GetMapping("/{userId}/{status}")
    public void check(@PathVariable("userId") String userId,
                      @PathVariable("status") EsStatus status) {
        ProcessTicketUpdateCommand command = new ProcessTicketUpdateCommand(userId, status);
        commandHandler.apply(command);

    }

}
