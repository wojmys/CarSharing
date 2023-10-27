package com.example.carsharing.repository;

import com.example.carsharing.domain.Car;
import com.example.carsharing.domain.Fuel;
import com.example.carsharing.domain.Status;
import com.example.carsharing.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class CarRepositoryTestSuite {

    @Autowired
    private CarRepository carRepository;

    private Car car;

    @BeforeEach
    void setUp() {
        car = Car.builder()
                .mark("Opel")
                .model("Astra")
                .motorCapacity(1.6)
                .fuel(Fuel.DIESEL)
                .status(Status.AVAILABLE)
                .build();
        carRepository.save(car);
    }

    @Test
    void createCar() {
        // then
        assertNotNull(car);
        //clean
        carRepository.deleteById(car.getId());
    }



}
