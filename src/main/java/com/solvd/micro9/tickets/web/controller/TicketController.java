package com.solvd.micro9.tickets.web.controller;

import com.solvd.micro9.tickets.domain.Ticket;
import com.solvd.micro9.tickets.service.TicketService;
import com.solvd.micro9.tickets.web.dto.TicketDto;
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

    private final TicketService ticketService;
    private final TicketMapper ticketMapper;

    @PostMapping
    public Mono<TicketDto> create(@Validated(CreateTicketGroup.class) @RequestBody TicketDto ticketDto) {
        Ticket ticket = ticketMapper.dtoToDomain(ticketDto);
        Mono<Ticket> ticketMono = ticketService.create(ticket);
        return ticketMapper.domainToDto(ticketMono);
    }

    @GetMapping
    public Flux<TicketDto> getAll() {
        Flux<Ticket> tickets = ticketService.getAll();
        return ticketMapper.domainToDto(tickets);
    }

}
