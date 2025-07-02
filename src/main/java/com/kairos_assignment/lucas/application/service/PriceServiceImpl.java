package com.kairos_assignment.lucas.application.service;

import com.kairos_assignment.lucas.application.dto.PriceResponseDTO;
import com.kairos_assignment.lucas.application.exception.PriceNotFoundException;
import com.kairos_assignment.lucas.application.mapper.PriceMapper;
import com.kairos_assignment.lucas.domain.entity.Price;
import com.kairos_assignment.lucas.domain.persistence.PriceRepository;
import com.kairos_assignment.lucas.domain.service.PriceService;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Comparator;
import java.util.List;

@Service
public class PriceServiceImpl implements PriceService {

    private final PriceRepository priceRepository;

    private final PriceMapper priceMapper;

    public PriceServiceImpl(PriceRepository priceRepository, PriceMapper priceMapper) {
        this.priceRepository = priceRepository;
        this.priceMapper = priceMapper;
    }


    @Override
    public PriceResponseDTO findApplicablePrice(Instant date, Long productId, Long brandId) {
        List<Price> applicablePrices = priceRepository.findApplicablePrices(date, productId, brandId);
        Price highestPriorityPrice = applicablePrices
                .stream()
                .max(Comparator.comparing(Price::getPriority))
                .orElseThrow(() -> new PriceNotFoundException("Price not found for given parameters"));

        return priceMapper.toDto(highestPriorityPrice);
    }
}
