package com.solvd.micro9.tickets.domain.cache;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketCache {

    private Long id;
    private String userId;

}
