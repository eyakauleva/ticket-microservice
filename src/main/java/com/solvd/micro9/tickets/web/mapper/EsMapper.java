package com.solvd.micro9.tickets.web.mapper;

import com.solvd.micro9.tickets.domain.es.Es;
import com.solvd.micro9.tickets.web.dto.EsDto;
import org.mapstruct.Mapper;
import reactor.core.publisher.Mono;

@Mapper(componentModel = "spring")
public interface EsMapper {

    EsDto domainToDto(Es eventStore);

    default Mono<EsDto> domainToDto(Mono<? extends Es> esMono) {
        return esMono.map(this::domainToDto);
    }

}
