package com.solvd.micro9.tickets.domain.command;

import com.solvd.micro9.tickets.domain.Event;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateEventCommand {

    private Event event;
    private String commandBy;

}
