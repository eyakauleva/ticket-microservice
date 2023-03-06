package com.solvd.micro9.tickets.web.controller;

import com.solvd.micro9.tickets.domain.Ticket;
import com.solvd.micro9.tickets.service.TicketService;
import com.solvd.micro9.tickets.web.dto.TicketDto;
import com.solvd.micro9.tickets.web.mapper.TicketMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tickets")
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;
    private final TicketMapper ticketMapper;

    @PostMapping
    public TicketDto create(@RequestBody @Validated TicketDto ticketDto) {
        Ticket ticket = ticketMapper.dtoToDomain(ticketDto);
        ticket = ticketService.create(ticket);
        return ticketMapper.domainToDto(ticket);
    }

    @GetMapping
    public List<TicketDto> getAll() {
        List<Ticket> tickets = ticketService.getAll();
        return ticketMapper.domainToDto(tickets);
    }

}
