package com.solvd.micro9.tickets.domain.command;

import com.solvd.micro9.tickets.domain.es.EsStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProcessTicketUpdateCommand {

    private String userId;
    private EsStatus status;

}
