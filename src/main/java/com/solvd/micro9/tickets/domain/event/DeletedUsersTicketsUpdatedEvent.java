package com.solvd.micro9.tickets.domain.event;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeletedUsersTicketsUpdatedEvent {

    private Long userId;

}
