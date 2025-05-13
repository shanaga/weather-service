package com.wellsfargo.controller;

import com.wellsfargo.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/weather/v1")
@CrossOrigin(origins = "http://localhost:4200")
public class WeatherAppController {

    @Autowired
    private WeatherService weatherService;

    @GetMapping("/city")
    public ResponseEntity<?> getCityDetails(@RequestParam String name) {
        return weatherService.getCityDetails(name);
    }

    @GetMapping("/temperature")
    public ResponseEntity<?> getTemperatures(@RequestParam String latitude, @RequestParam String longitude) {
        return weatherService.getTemperature(latitude, longitude);
    }
}