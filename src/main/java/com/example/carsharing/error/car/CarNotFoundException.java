package com.example.carsharing.error.car;

import lombok.Getter;

    @Getter
    public class CarNotFoundException extends RuntimeException {

        private final Long id;

        public CarNotFoundException(Long id) {
            super("CAR_ID not found");
            this.id = id;
        }
}
