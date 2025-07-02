package com.kairos_assignment.lucas.domain.persistence;

import com.kairos_assignment.lucas.domain.entity.Price;

import java.time.Instant;
import java.util.List;

public interface PriceRepository {

    List<Price> findApplicablePrices(Instant date, Long productId, Long brandId);
}
