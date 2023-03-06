package com.solvd.micro9.tickets.service;

import com.solvd.micro9.tickets.domain.Ticket;

import java.util.List;

public interface TicketService {

    Ticket create(Ticket ticket);

    List<Ticket> getAll();

}
