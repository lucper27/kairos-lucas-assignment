package com.kairos_assignment.lucas.infrastructure.rest;

import com.kairos_assignment.lucas.application.dto.PriceResponseDTO;
import com.kairos_assignment.lucas.domain.service.PriceService;
import com.kairos_assignment.lucas.application.exception.BadRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RestController
@RequestMapping("/api/prices")
public class PriceResource {

    private final Logger log = LoggerFactory.getLogger(PriceResource.class);

    private final PriceService priceService;

    public PriceResource(PriceService priceService) {
        this.priceService = priceService;
    }

    @GetMapping
    public ResponseEntity<PriceResponseDTO> getPrice(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Instant date,
            @RequestParam Long productId,
            @RequestParam Long brandId
    ) {
        log.debug("Rest request to find applicable price with params {}, {}, {} ", date, productId, brandId);

        PriceResponseDTO response = priceService.findApplicablePrice(date, productId, brandId);

        return ResponseEntity.ok(response);
    }
}
