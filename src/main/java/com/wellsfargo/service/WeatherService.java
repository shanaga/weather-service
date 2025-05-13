package com.wellsfargo.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
public class WeatherService {

    private final RestTemplate restTemplate = new RestTemplate();
    @Value("${weather.api.city.url}")
    private String cityApiUrl;
    @Value("${weather.api.temperature.url}")
    private String temperatureApiUrl;

    public ResponseEntity<?> getCityDetails(String city) {
        try {
            Map response = restTemplate.getForObject(cityApiUrl, Map.class, city);
            List cityDataList = (List) response.get("results");
            return ResponseEntity.ok(cityDataList);
        } catch (Exception ex) {
            throw new RuntimeException("Error fetching city details", ex);
        }
    }

    public ResponseEntity<?> getTemperature(String latitude, String longitude) {
        try {
            Map response = restTemplate.getForObject(temperatureApiUrl, Map.class, latitude, longitude);
            Map currentWeather = (Map) response.get("current_weather");
            String temperature = currentWeather.get("temperature").toString();
            return ResponseEntity.ok(temperature);
        } catch (Exception ex) {
            throw new RuntimeException("Error fetching temperature", ex);
        }
    }
}