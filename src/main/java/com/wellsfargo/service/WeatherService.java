package com.wellsfargo.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WeatherService {

    public ResponseEntity<? extends Map> getCityDetails(String city) {
        String url = "http://geocoding-api.open-meteo.com/v1/search/?name=" + city;
        RestTemplate restTemplate = new RestTemplate();
        Map response = restTemplate.getForObject(url, Map.class);
        System.out.println(response);
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<Map<String, Object>> getTemperatures(List<String> cities) {
        Map<String, Object> result = new HashMap<>();

        for (String city: cities) {
            double latitude = 40.7128;
            double longitude = -74.0060;

            String url = String.format("http://api.open-meteo.com/v1/forecast?latitude=%f&longitude=%f&current_weather=true", latitude, longitude);
            // Example : https://api.open-meteo.com/v1/forecast?latitude=40.7128&longitude=-74.0060&current_weather=true
            RestTemplate restTemplate = new RestTemplate();
            Map response = restTemplate.getForObject(url, Map.class);
            Map currentWeather = (Map) response.get("current_weather");
            result.put(city, currentWeather.get("temperature"));
        }
        return ResponseEntity.ok(result);
    }


}
