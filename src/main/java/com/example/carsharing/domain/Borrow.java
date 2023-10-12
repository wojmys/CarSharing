package com.example.carsharing.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Borrow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne
    @JoinColumn(name = "CAR_ID")
    private Car car;
    private LocalDate borrowDate;
    private LocalDate returnDate;
    private double distanceTravelledInKm;
    private double totalFuelCost;
    private Status status;


    private double checkFuelFee() {
        double result=0;
        switch (car.getFuel()) {
            case LPG -> { result=0.2;}
            case DIESEL -> {return 0.3;}
            case PETROL -> {return 0.5;}
            case ELECTRIC -> {return 0.4;}
        };
        return result;
    }

    public void calculateTotalFuelCost(Fuel fuelType) {
        double fuelFee = checkFuelFee();
        if (this.distanceTravelledInKm == 0) {
            setTotalFuelCost(0);
        } else {
            setTotalFuelCost(fuelFee * totalFuelCost);
        }
    }

}
