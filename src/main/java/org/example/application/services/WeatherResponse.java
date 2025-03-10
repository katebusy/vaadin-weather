package org.example.application.services;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherResponse {
    public Main main;
    public List<Weather> weather;

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Main {
        public double temp;
        public double feels_like;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Weather {
        public int id;
        public String description;
        public String icon;
    }
}
