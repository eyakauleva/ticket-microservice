package com.solvd.micro9.tickets.domain.command;

import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
public class UpdateDeletedUserTicketsCommand {

    @TargetAggregateIdentifier
    private Long userId;

}
