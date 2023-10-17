package com.example.carsharing.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@Builder
public class RentDto {

    private Long id;
    private Long userId;
    private Long carId;
    private LocalDate rentDate;
    private LocalDate returnDate;
    private double distanceTravelledInKm;
    private double totalFuelCost;
}
