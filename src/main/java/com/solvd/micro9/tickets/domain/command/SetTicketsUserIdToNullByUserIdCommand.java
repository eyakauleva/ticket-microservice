package com.solvd.micro9.tickets.domain.command;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SetTicketsUserIdToNullByUserIdCommand {

    private final String userId;
    private final String commandBy;

    public SetTicketsUserIdToNullByUserIdCommand(String userId) {
        this.userId = userId;
        this.commandBy = null;
    }

}
