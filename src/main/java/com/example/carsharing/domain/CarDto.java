package com.example.carsharing.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class CarDto {
    private Long id;
    private String mark;
    private String model;
    private double motorCapacity;
    private Fuel fuel;
}