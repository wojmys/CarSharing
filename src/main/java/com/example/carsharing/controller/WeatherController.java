package com.example.carsharing.controller;

import com.example.carsharing.domain.WeatherDto;
import com.example.carsharing.domain.weather.CurrentWeather;
import com.example.carsharing.weather.client.WeatherClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("*")
@RequestMapping("/weather")
public class WeatherController {

    @Autowired
    private WeatherClient weatherClient;

    @GetMapping
    public CurrentWeather getCurrentWeather(){
        String uri= weatherClient.getWeatherApiEndpoint();
        RestTemplate template = new RestTemplate();
        Object[] objects = new Object[]{template.getForObject(uri, Object.class)};

        ObjectMapper objectMapper = new ObjectMapper();
        return Arrays.stream(objects)
             .map(o->objectMapper.convertValue(o,WeatherDto.class))
                     .map(WeatherDto::getCurrentWeather)
             .collect(Collectors.toList()).get(0);
    }
}