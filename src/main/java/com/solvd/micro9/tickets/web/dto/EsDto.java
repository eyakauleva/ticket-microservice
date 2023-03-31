package com.solvd.micro9.tickets.web.dto;

import com.solvd.micro9.tickets.domain.es.EsEventType;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class EsDto {

    private Long id;
    private EsEventType type;
    private LocalDateTime time;
    private String createdBy;
    private String entityId;
    private String payload;

}
