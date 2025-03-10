package org.example.application.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.application.entities.WeatherRequest;
import org.example.application.repositories.WeatherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class WeatherService {
    private final String API_KEY = "d3dfc4b29edea977b79970a7fb4ddc71";
    private final String BASE_URL = "https://api.openweathermap.org/data/2.5/weather";
    private final WeatherRepository repository;

    @Autowired
    public WeatherService(WeatherRepository repository) {
        this.repository = repository;
    }

    public WeatherResponse getWeather(double latitudeValue, double longitudeValue) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        String url = String.format(
                "%s?lat=%f&lon=%f&appid=%s&lang=ru&units=metric",
                BASE_URL,
                latitudeValue,
                longitudeValue,
                API_KEY);
        String response = restTemplate.getForObject(url, String.class);

        ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.readValue(response, WeatherResponse.class);
    }

    public WeatherRequest saveRequest(String latitudeValue, String longitudeValue, WeatherResponse weatherResponse) {
        WeatherRequest weatherRequest = WeatherRequest.builder()
                .longitude(longitudeValue)
                .latitude(latitudeValue)
                .answer(weatherResponse.weather.get(0).description)
                .build();
        return repository.saveAndFlush(weatherRequest);
    }

    public List<WeatherRequest> getAllRequests() {
        return repository.findAll();
    }
}
