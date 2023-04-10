package com.solvd.micro9.tickets.domain.command;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeleteTicketsUserByUserIdCommand {

    private final String userId;
    private final String commandBy;

    public DeleteTicketsUserByUserIdCommand(String userId) {
        this.userId = userId;
        this.commandBy = null;
    }

}
