package com.kairos_assignment.lucas.application.service;

import com.kairos_assignment.lucas.application.dto.PriceResponseDTO;
import com.kairos_assignment.lucas.domain.service.PriceService;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class PriceServiceImpl implements PriceService {

    @Override
    public PriceResponseDTO findApplicablePrice(Instant date, Long productId, Long brandId) {
        return null;
    }
}
