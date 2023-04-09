package com.solvd.micro9.tickets.domain.command;

import com.solvd.micro9.tickets.domain.aggregate.Event;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateEventCommand {

    private final Event event;
    private final String commandBy;

}
