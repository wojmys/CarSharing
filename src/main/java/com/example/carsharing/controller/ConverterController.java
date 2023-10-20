package com.example.carsharing.controller;

import com.example.carsharing.domain.CurrencyDto;
import com.example.carsharing.domain.Rent;
import com.example.carsharing.domain.RentDto;
import com.example.carsharing.domain.WeatherDto;
import com.example.carsharing.domain.currency.Rates;
import com.example.carsharing.domain.weather.CurrentWeather;
import com.example.carsharing.weather.client.CurrencyClient;
import com.example.carsharing.weather.client.WeatherClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("*")
@RequestMapping("/converter")
public class ConverterController {

    @Autowired
    private CurrencyClient currencyClient;

    @GetMapping
    public Rates getConvertedRates() {
        String uri = currencyClient.getCurrencyApiEndpoint();
        RestTemplate template = new RestTemplate();
        Object[] objects = new Object[]{template.getForObject(uri, Object.class)};

        ObjectMapper objectMapper = new ObjectMapper();
        return Arrays.stream(objects)
                .map(o -> objectMapper.convertValue(o, CurrencyDto.class))
                .map(CurrencyDto::getRates)
                .collect(Collectors.toList()).get(0);
    }
}