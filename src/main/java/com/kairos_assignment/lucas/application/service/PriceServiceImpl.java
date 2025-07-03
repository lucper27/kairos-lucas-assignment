package com.kairos_assignment.lucas.application.service;

import com.kairos_assignment.lucas.application.dto.PriceResponseDTO;
import com.kairos_assignment.lucas.application.exception.BadRequestException;
import com.kairos_assignment.lucas.application.exception.PriceNotFoundException;
import com.kairos_assignment.lucas.application.mapper.PriceMapper;
import com.kairos_assignment.lucas.domain.entity.Price;
import com.kairos_assignment.lucas.domain.persistence.PriceRepository;
import com.kairos_assignment.lucas.domain.service.PriceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Comparator;
import java.util.List;

@Service
public class PriceServiceImpl implements PriceService {

    private final Logger log = LoggerFactory.getLogger(PriceServiceImpl.class);

    private final PriceRepository priceRepository;

    private final PriceMapper priceMapper;

    public PriceServiceImpl(PriceRepository priceRepository, PriceMapper priceMapper) {
        this.priceRepository = priceRepository;
        this.priceMapper = priceMapper;
    }

    @Override
    public PriceResponseDTO findApplicablePrice(Instant date, Long productId, Long brandId) {
        log.debug("finding applicable price with params {}, {}, {}", date, productId, brandId);
        if (date == null || productId == null || brandId == null) {
            throw new BadRequestException("Date, productId, and brandId must not be null.");
        }

        List<Price> applicablePrices = priceRepository.findApplicablePrices(date, productId, brandId);

        Price highestPriorityPrice = applicablePrices
                .stream()
                .max(Comparator.comparing(Price::getPriority))
                .orElseThrow(() -> new PriceNotFoundException("Price not found"));

        return priceMapper.toDto(highestPriorityPrice);
    }
}
