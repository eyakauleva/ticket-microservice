package com.solvd.micro9.tickets.domain.command;

import com.solvd.micro9.tickets.domain.aggregate.Ticket;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateTicketCommand {

    private final Ticket ticket;
    private final String commandBy;

}
