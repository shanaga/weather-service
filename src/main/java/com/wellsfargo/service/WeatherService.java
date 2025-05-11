package com.wellsfargo.service;

import com.wellsfargo.model.CityCoordinates;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WeatherService {

    /**
     * Returns the city details based on input with city name
     *
     * @param city
     * @return
     */
    public ResponseEntity<?> getCityDetails(String city) {
        // Working code
        String url = "http://geocoding-api.open-meteo.com/v1/search/?name=" + city;
        RestTemplate restTemplate = new RestTemplate();
        Map response = restTemplate.getForObject(url, Map.class);
        List<String> cityDataList = (List) response.get("results");
        return ResponseEntity.ok(cityDataList);
    }

    /**
     * Returns the temperature of the provided latitude and longitude coordinates
     *
     * @param coordinates
     * @return
     */
    public ResponseEntity<Map<String, Object>> getTemperature(CityCoordinates coordinates) {
        Map<String, Object> result = new HashMap<>();
        String url = String.format("http://api.open-meteo.com/v1/forecast?latitude=%f&longitude=%f&current_weather=true", coordinates.getLatitude(), coordinates.getLongitude());
        RestTemplate restTemplate = new RestTemplate();
        Map response = restTemplate.getForObject(url, Map.class);
        Map currentWeather = (Map) response.get("current_weather");
        result.put(coordinates.getCity(), currentWeather.get("temperature"));
        return ResponseEntity.ok(result);
    }

}
