package com.solvd.micro9.tickets.domain.aggregate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tickets")
public class Ticket {

    @Id
    private String id;

    @Column("user_id")
    private String userId;

    @Column("event_id")
    private String eventId;

    private Integer quantity;

    private BigDecimal price;

}
