package com.example.carsharing.repository;

import com.example.carsharing.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class RentRepositoryTestSuite {


    @Autowired
    private RentRepository rentRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CarRepository carRepository;

    private Rent rent;
    private User user;
    private Car car;


    @BeforeEach
    void setUp(){
        user = User.builder()
                .name("Stefan Moris")
                .isTopCustomer(false)
                .build();
        userRepository.save(user);

        car = Car.builder()
                .mark("Mercedes")
                .model("A-class")
                .status(Status.AVAILABLE)
                .motorCapacity(2.0)
                .fuel(Fuel.LPG)
                .build();
        carRepository.save(car);

        rent= Rent.builder()
                .car(car)
                .user(user)
                .rentDate(LocalDate.of(2023,10,23))
                .returnDate((LocalDate.of(2023,10,30)))
                .build();
        rentRepository.save(rent);
    }

    @Test
    void createRentalTest(){
        //then
        assertNotNull(rent);
        //clean
        rentRepository.deleteById(rent.getId());
        carRepository.deleteById(car.getId());
        userRepository.deleteById(user.getId());
    }
}
