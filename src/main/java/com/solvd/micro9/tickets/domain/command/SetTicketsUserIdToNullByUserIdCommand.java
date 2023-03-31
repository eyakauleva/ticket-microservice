package com.solvd.micro9.tickets.domain.command;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SetTicketsUserIdToNullByUserIdCommand {

    private final Long userId;
    private final String commandBy;

    public SetTicketsUserIdToNullByUserIdCommand(Long userId) {
        this.userId = userId;
        this.commandBy = null;
    }

}
