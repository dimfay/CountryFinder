package com.countryfinder.packages.controllers.rest;


import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@SpringBootTest
@AutoConfigureMockMvc
class MainControllerIntegrationTest {

    @Autowired
    MockMvc mockMvc;
    @Test
    public void shouldWorkWithoutErrorsLatvia() throws Exception {
        String findCountryRequest = "{\"number\": \"+37124980543\"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/api/find-country")
                .content(findCountryRequest)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.countryName").value("Latvia"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.phoneNumber").value("+37124980543"));
    }

    @Test
    public void shouldWorkWithoutErrorsRussia() throws Exception {
        String findCountryRequest = "{\"number\": \"+79221234561\"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/api/find-country")
                .content(findCountryRequest)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.countryName").value("Russia"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.phoneNumber").value("+79221234561"));
    }

    @Test
    public void shouldReturnErrors() throws Exception{
        String findCountryRequest = "{\"number\": \"+7412\"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/api/find-country")
                .content(findCountryRequest)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.errors", Matchers.hasItem("number: length must be between 7 and 16")))
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.errors", Matchers.hasItem("number: wrong phone number format")));
    }

}