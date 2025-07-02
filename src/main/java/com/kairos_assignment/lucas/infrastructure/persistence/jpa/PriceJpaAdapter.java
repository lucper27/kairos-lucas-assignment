package com.kairos_assignment.lucas.infrastructure.persistence.jpa;

import com.kairos_assignment.lucas.domain.persistence.PriceRepository;
import com.kairos_assignment.lucas.domain.entity.Price;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public class PriceJpaAdapter implements PriceRepository {

    final PriceJpaRepository priceJpaRepository;

    public PriceJpaAdapter(PriceJpaRepository priceJpaRepository) {
        this.priceJpaRepository = priceJpaRepository;
    }

    @Override
    public List<Price> findApplicablePrices(Instant date, Long productId, Long brandId) {
        return priceJpaRepository
                .findByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(brandId, productId, date, date);
    }

}
