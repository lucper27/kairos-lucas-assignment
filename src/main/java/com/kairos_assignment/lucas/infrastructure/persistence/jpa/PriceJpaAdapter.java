package com.kairos_assignment.lucas.infrastructure.persistence.jpa;

import com.kairos_assignment.lucas.domain.persistence.PriceRepository;
import com.kairos_assignment.lucas.domain.entity.Price;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public class PriceJpaAdapter implements PriceRepository {

    private final Logger log = LoggerFactory.getLogger(PriceJpaAdapter.class);

    private final PriceJpaRepository priceJpaRepository;

    public PriceJpaAdapter(PriceJpaRepository priceJpaRepository) {
        this.priceJpaRepository = priceJpaRepository;
    }

    @Override
    public List<Price> findApplicablePrices(Instant date, Long productId, Long brandId) {
        log.debug("finding applicable price in repository with params {}, {}, {}", date, productId, brandId);
        return priceJpaRepository
                .findByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(brandId, productId, date, date);
    }

}
