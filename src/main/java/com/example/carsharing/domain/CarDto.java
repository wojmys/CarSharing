package com.example.carsharing.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import java.util.List;

@Getter
@AllArgsConstructor
@Builder
public class CarDto {
    private Long id;
    private String mark;
    private String model;
    private double motorCapacity;
    private Fuel fuel;
    private List<Long> rentIds;
    private Status status;
}
