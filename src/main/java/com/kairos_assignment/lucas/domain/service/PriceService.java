package com.kairos_assignment.lucas.domain.service;

import com.kairos_assignment.lucas.application.dto.PriceResponseDTO;

import java.time.Instant;

public interface PriceService {

    PriceResponseDTO findApplicablePrice(Instant date, Long productId, Long brandId);
}
