package com.example.carsharing.weather.client;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@Component
@Getter
public class WeatherClient {
    private final RestTemplate restTemplate;

    @Value("${open.meteo.api.prod}")
    private String weatherApiEndpoint;

// second implement option
//    public List<WeatherDto> getWeather() {
//        URI url = UriComponentsBuilder.fromHttpUrl(weatherApiEndpoint)
//                .queryParam("current", "time,temperature_2m")
//                .build()
//                .encode()
//                .toUri();
//
//        WeatherDto[] weatherResponse = restTemplate.getForObject(url, WeatherDto[].class);
//
//        return Optional.ofNullable(weatherResponse)
//                .map(Arrays::asList)
//                .orElse(Collections.emptyList());
//    }
}