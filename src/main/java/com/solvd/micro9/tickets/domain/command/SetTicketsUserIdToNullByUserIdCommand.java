package com.solvd.micro9.tickets.domain.command;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SetTicketsUserIdToNullByUserIdCommand {

    private Long userId;
    private String commandBy;

}
