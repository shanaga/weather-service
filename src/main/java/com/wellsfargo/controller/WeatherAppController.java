package com.wellsfargo.controller;

import com.wellsfargo.service.WeatherService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/weather")
public class WeatherAppController {

    @Autowired
    WeatherService weatherService;

    @GetMapping("/city")
    public ResponseEntity<? extends  Map> getCityDetails(@RequestParam String name) {
        // http://localhost:8080/weather/city?name=Herndon
        return weatherService.getCityDetails(name);
    }

    @GetMapping("")
    public ResponseEntity<Map<String, Object>> getTemperatures(@RequestParam List<String> cities) {
        // Hit local api url : http://localhost:8080/weather?cities=NewYork,LosAngeles
        return weatherService.getTemperatures(cities);
    }


}
