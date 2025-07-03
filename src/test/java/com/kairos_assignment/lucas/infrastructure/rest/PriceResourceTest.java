package com.kairos_assignment.lucas.infrastructure.rest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class PriceResourceTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void test1_10amDay14_ShouldReturnPriceList1() throws Exception {
        mockMvc.perform(get("/api/prices")
                        .param("date", "2020-06-14T10:00:00Z")
                        .param("productId", "35455")
                        .param("brandId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.priceList").value(1))
                .andExpect(jsonPath("$.price").value(35.50));
    }

    @Test
    void test2_4pmDay14_ShouldReturnPriceList2() throws Exception {
        mockMvc.perform(get("/api/prices")
                        .param("date", "2020-06-14T16:00:00Z")
                        .param("productId", "35455")
                        .param("brandId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.priceList").value(2))
                .andExpect(jsonPath("$.price").value(25.45));
    }

    @Test
    void test3_21pmDay14_ShouldReturnPriceList1() throws Exception {
        mockMvc.perform(get("/api/prices")
                        .param("date", "2020-06-14T21:00:00Z")
                        .param("productId", "35455")
                        .param("brandId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.priceList").value(1))
                .andExpect(jsonPath("$.price").value(35.5));
    }

    @Test
    void test4_4pmDay15_ShouldReturnPriceList4() throws Exception {
        mockMvc.perform(get("/api/prices")
                        .param("date", "2020-06-15T16:00:00Z")
                        .param("productId", "35455")
                        .param("brandId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.priceList").value(4))
                .andExpect(jsonPath("$.price").value(38.95));
    }

    @Test
    void test5_21pmDay16_ShouldReturnPriceList4() throws Exception {
        mockMvc.perform(get("/api/prices")
                        .param("date", "2020-06-16T21:00:00Z")
                        .param("productId", "35455")
                        .param("brandId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.priceList").value(4))
                .andExpect(jsonPath("$.price").value(38.95));
    }

    @Test
    void shouldReturnBadRequestWhenDateIsMissing() throws Exception {
        mockMvc.perform(get("/api/prices")
                        .param("productId", "35455")
                        .param("brandId", "1"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnBadRequestWhenProductIdIsMissing() throws Exception {
        mockMvc.perform(get("/api/prices")
                        .param("date", "2020-06-14T10:00:00Z")
                        .param("brandId", "1"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnBadRequestWhenBrandIdIsMissing() throws Exception {
        mockMvc.perform(get("/api/prices")
                        .param("date", "2020-06-14T10:00:00Z")
                        .param("productId", "35455"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnNotFoundWhenNoPriceFound() throws Exception {
        mockMvc.perform(get("/api/prices")
                        .param("date", "2099-01-01T00:00:00Z")
                        .param("productId", "99999")
                        .param("brandId", "999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnBadRequestWhenParameterTypeIsInvalid() throws Exception {
        mockMvc.perform(get("/api/prices")
                        .param("date", "2020-06-14T10:00:00Z")
                        .param("productId", "35455")
                        .param("brandId", "invalidBrandId"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.message").value("Invalid value 'invalidBrandId' for parameter 'brandId'. Expected type is 'Long'."));
    }
    @Test
    void shouldReturnBadRequestWhenProductIdTypeIsInvalid() throws Exception {
        mockMvc.perform(get("/api/prices")
                        .param("date", "2020-06-14T10:00:00Z")
                        .param("productId", "invalidProductId")
                        .param("brandId", "1"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.message").value("Invalid value 'invalidProductId' for parameter 'productId'. Expected type is 'Long'."));
    }

    @Test
    void shouldReturnBadRequestWhenDateTypeIsInvalid() throws Exception {
        mockMvc.perform(get("/api/prices")
                        .param("date", "invalidDate")
                        .param("productId", "35455")
                        .param("brandId", "1"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.message").value("Invalid value 'invalidDate' for parameter 'date'. Expected type is 'Instant'."));
    }

}
