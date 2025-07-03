package com.kairos_assignment.lucas.application.service;


import com.kairos_assignment.lucas.application.dto.PriceResponseDTO;
import com.kairos_assignment.lucas.application.exception.BadRequestException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class PriceServiceTest {

    @Autowired
    private PriceServiceImpl priceService;

    private final Long testBrandId = 1L;
    private final Long testProductId = 35455L;

    @Test
    void test1_10amDay14_ShouldReturnPriceList1() {
        Instant testDate = toInstant("2020-06-14T10:00:00");
        PriceResponseDTO result = priceService.findApplicablePrice(testDate, testProductId, testBrandId);

        assertAll(
                () -> assertEquals(1, result.getPriceList()),
                () -> assertEquals(35.50, result.getPrice().doubleValue())
        );
    }

    @Test
    void test2_4pmDay14_ShouldReturnPriceList2() {
        Instant testDate = toInstant("2020-06-14T16:00:00");
        PriceResponseDTO result = priceService.findApplicablePrice(testDate, testProductId, testBrandId);
        assertEquals(2, result.getPriceList());
    }

    @Test
    void test3_21pmDay14_ShouldReturnPriceList1() {
        Instant testDate = toInstant("2020-06-14T21:00:00");
        PriceResponseDTO result = priceService.findApplicablePrice(testDate, testProductId, testBrandId);
        assertEquals(1, result.getPriceList());
    }

    @Test
    void test4_4pmDay15_ShouldReturnPriceList4() {
        Instant testDate = toInstant("2020-06-15T16:00:00");
        PriceResponseDTO result = priceService.findApplicablePrice(testDate, testProductId, testBrandId);
        assertEquals(4, result.getPriceList());
    }

    @Test
    void test5_21pmDay16_ShouldReturnPriceList4() {
        Instant testDate = toInstant("2020-06-16T21:00:00");
        PriceResponseDTO result = priceService.findApplicablePrice(testDate, testProductId, testBrandId);
        assertEquals(4, result.getPriceList());
    }

    @Test
    void shouldThrowBadRequestExceptionWhenDateIsNull() {
        Long productId = 35455L;
        Long brandId = 1L;

        BadRequestException exception = assertThrows(
                BadRequestException.class,
                () -> priceService.findApplicablePrice(null, productId, brandId)
        );

        assertEquals("Date, productId, and brandId must not be null.", exception.getMessage());
    }

    @Test
    void shouldThrowBadRequestExceptionWhenProductIdIsNull() {
        Instant testDate = toInstant("2020-06-14T10:00:00");
        Long brandId = 1L;

        BadRequestException exception = assertThrows(
                BadRequestException.class,
                () -> priceService.findApplicablePrice(testDate, null, brandId)
        );

        assertEquals("Date, productId, and brandId must not be null.", exception.getMessage());
    }

    @Test
    void shouldThrowBadRequestExceptionWhenBrandIdIsNull() {
        Instant testDate = toInstant("2020-06-14T10:00:00");
        Long productId = 35455L;

        BadRequestException exception = assertThrows(
                BadRequestException.class,
                () -> priceService.findApplicablePrice(testDate, productId, null)
        );

        assertEquals("Date, productId, and brandId must not be null.", exception.getMessage());
    }

    private Instant toInstant(String dateTime) {
        return LocalDateTime.parse(dateTime).toInstant(ZoneOffset.UTC);
    }
}
