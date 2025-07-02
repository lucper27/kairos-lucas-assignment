package com.kairos_assignment.lucas.infrastructure.rest;

import com.kairos_assignment.lucas.application.dto.PriceResponseDTO;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/prices")
public class PriceResource {

    @GetMapping
    public ResponseEntity<PriceResponseDTO> getPrice(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date,
            @RequestParam Long productId,
            @RequestParam Long brandId
    ) {
//        PriceResponseDTO response = priceService.findPrice(date, productId, brandId);
        PriceResponseDTO response = new PriceResponseDTO();
        return ResponseEntity.ok(response);
    }
}
