package com.example.carsharing.error.borrow;

import lombok.Getter;

@Getter
public class RentNotFoundException extends RuntimeException{

    private final Long id;

    public RentNotFoundException(Long id) {
        super("Borrow_ID not found");
        this.id = id;
    }
}
