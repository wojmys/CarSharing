package com.example.carsharing.error.rent;

import lombok.Getter;

@Getter
public class CarNotAvailableException extends RuntimeException{

    public CarNotAvailableException() {
        super("Rental not available for this date");
    }
}