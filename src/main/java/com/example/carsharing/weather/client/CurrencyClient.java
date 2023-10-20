package com.example.carsharing.weather.client;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;




@RequiredArgsConstructor
@Component
@Getter
public class CurrencyClient {
    private final RestTemplate restTemplate;

    @Value("${open.currency.api.prod}")
    private String currencyApiEndpoint;


}
