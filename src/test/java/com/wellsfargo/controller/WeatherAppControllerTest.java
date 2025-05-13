package com.wellsfargo.controller;

import com.wellsfargo.service.WeatherService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(WeatherAppController.class)
public class WeatherAppControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private WeatherService weatherService;

    @BeforeEach
    void setUp() {
        reset(weatherService);
    }

    @Test
    @DisplayName("Test case to test get city details")
    @Disabled
    void testGetCityDetails() throws Exception {
        List<String> mockCities = Arrays.asList("Richmond", "Richmond Hill");
        when(weatherService.getCityDetails("Richmond")).thenAnswer(invocation -> {
            List<String> result = Arrays.asList("Richmond", "Richmond Hill");
            return ResponseEntity.ok(result);
        });
        mockMvc.perform(get("/weather/v1/city?name=Richmond"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]").value("Richmond"));
    }

    @Test
    @DisplayName("Test case to test get temperature")
    @Disabled
    void testGetTemperature() throws Exception {
        when(weatherService.getTemperature("37.55", "-77.46")).thenAnswer(invocation -> {
            String temperature = "15.4";
            return ResponseEntity.ok(temperature);
        });
        mockMvc.perform(get("/weather/v1/temperature?latitude=37.55&longitude=-77.46"))
                .andExpect(status().isOk())
                .andExpect(content().string("22.5"));
    }
}