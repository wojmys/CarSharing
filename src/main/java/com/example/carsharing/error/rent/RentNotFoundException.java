package com.example.carsharing.error.rent;

import lombok.Getter;

@Getter
public class RentNotFoundException extends RuntimeException{

    private final Long id;

    public RentNotFoundException(Long id) {
        super("Rent_ID not found");
        this.id = id;
    }
}
