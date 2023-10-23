package com.example.carsharing.domain;

import jakarta.persistence.*;
import lombok.*;
import javax.validation.constraints.NotNull;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Rent {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne
    @JoinColumn(name = "CAR_ID")
    private Car car;
    private LocalDate rentDate;
    private LocalDate returnDate;
    private double distanceTravelledInKm;
    private double totalFuelCost;

    private double checkFuelFee() {
        double result=0;
        switch (car.getFuel()) {
            case LPG ->  result=0.2;
            case DIESEL -> result= 0.3;
            case PETROL -> result=0.5;
            case ELECTRIC -> result= 0.4;
        }
        return result;
    }

    public double calculateTotalFuelCost() {
        double fuelFee = checkFuelFee();
        if (this.distanceTravelledInKm == 0) {
            return 0;
        } return fuelFee*distanceTravelledInKm;
    }
}
