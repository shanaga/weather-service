package com.wellsfargo.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class WeatherServiceTest {

    @InjectMocks
    private WeatherService weatherService;

    @Mock
    private RestTemplate restTemplate;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Test case to test get city details")
    @Disabled
    void testGetCityDetails_returnsCityList() {
        // Mock response
        Map<String, Object> mockResponse = new HashMap<>();
        List<String> cityList = Arrays.asList("Richmond", "Richmond Hill");
        mockResponse.put("results", cityList);

        WeatherService spyService = spy(weatherService);
        doReturn(restTemplate).when(spyService).getCityDetails(anyString()); // If extracted

        when(restTemplate.getForObject(anyString(), eq(Map.class)))
                .thenReturn(mockResponse);

        ResponseEntity<?> response = spyService.getCityDetails("Richmond");

        assertEquals(200, response.getStatusCodeValue());
        assertTrue(((List<?>) response.getBody()).contains("Richmond"));
    }

    @Test
    @DisplayName("Test case to test get temperature")
    @Disabled
    void testGetTemperature_returnsTemperature() {
        Map<String, Object> currentWeather = new HashMap<>();
        currentWeather.put("temperature", 22.5);
        Map<String, Object> mockResponse = new HashMap<>();
        mockResponse.put("current_weather", currentWeather);

        WeatherService spyService = spy(weatherService);
        doReturn(restTemplate).when(spyService).getTemperature(anyString(), anyString()); // If extracted

        when(restTemplate.getForObject(anyString(), eq(Map.class)))
                .thenReturn(mockResponse);

        ResponseEntity<?> response = spyService.getTemperature("37.55", "-77.46");

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("22.5", response.getBody());
    }
}