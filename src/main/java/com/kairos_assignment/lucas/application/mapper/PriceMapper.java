package com.kairos_assignment.lucas.application.mapper;

import com.kairos_assignment.lucas.application.dto.PriceResponseDTO;
import com.kairos_assignment.lucas.domain.entity.Price;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PriceMapper extends EntityMapper<PriceResponseDTO, Price> {
}
