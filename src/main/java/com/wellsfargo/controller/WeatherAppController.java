package com.wellsfargo.controller;

import com.wellsfargo.model.CityCoordinates;
import com.wellsfargo.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/weather/v1")
public class WeatherAppController {

    @Autowired
    WeatherService weatherService;

    // http://localhost:8080/weather/v1/city?name=Richmond
    @GetMapping("/city")
    public ResponseEntity<?> getCityDetails(@RequestParam String name) {
        return weatherService.getCityDetails(name);
    }

    // http://localhost:8080/weather/v1/temperature
    @PostMapping("/temperature")
    public ResponseEntity<Map<String, Object>> getTemperatures(@RequestBody CityCoordinates coordinates) {
        return weatherService.getTemperature(coordinates);
    }

}
