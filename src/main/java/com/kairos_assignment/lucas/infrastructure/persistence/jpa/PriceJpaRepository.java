package com.kairos_assignment.lucas.infrastructure.persistence.jpa;

import com.kairos_assignment.lucas.domain.entity.Price;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;
import java.util.List;

public interface PriceJpaRepository extends JpaRepository<Price, Long> {

    List<Price> findByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
            Long brandId,
            Long productId,
            Instant date,
            Instant sameDate
    );
}
