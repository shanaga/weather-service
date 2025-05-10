package com.wellsfargo.service;

import com.wellsfargo.model.CityCoordinates;
import com.wellsfargo.model.CityData;
import com.wellsfargo.model.CityDataOriginal;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

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

//        // TODO : Not working
//        List<CityData> filteredList = cityDataList.stream()
//                .map(obj -> new CityData(obj.getName()))
//                .toList();
//        return ResponseEntity.ok(filteredList);
//
//
//        ResponseEntity<List<CityDataOriginal>> responseEntity = restTemplate.exchange(
//                url,
//                HttpMethod.GET,
//                null,
//                new ParameterizedTypeReference<>() {
//                }
//        );
//
//        List<CityDataOriginal> users = responseEntity.getBody();
//        return (ResponseEntity<?>) users.stream()
//                .map(CityDataOriginal::getName)
//                .collect(Collectors.toList());

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



//    public ResponseEntity<Map<String, Object>> getTemperatures(List<String> cities) {
//        Map<String, Object> result = new HashMap<>();
//
//        for (String city: cities) {
//            double latitude = 40.7128;
//            double longitude = -74.0060;
//
//            String url = String.format("http://api.open-meteo.com/v1/forecast?latitude=%f&longitude=%f&current_weather=true", latitude, longitude);
//            // Example : https://api.open-meteo.com/v1/forecast?latitude=40.7128&longitude=-74.0060&current_weather=true
//            RestTemplate restTemplate = new RestTemplate();
//            Map response = restTemplate.getForObject(url, Map.class);
//            Map currentWeather = (Map) response.get("current_weather");
//            result.put(city, currentWeather.get("temperature"));
//        }
//        return ResponseEntity.ok(result);
//    }


}
