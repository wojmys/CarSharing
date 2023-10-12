package com.example.carsharing.service;

import com.example.carsharing.domain.Car;
import com.example.carsharing.domain.User;
import com.example.carsharing.error.car.CarNotFoundException;
import com.example.carsharing.error.user.UserNotFoundException;
import com.example.carsharing.repository.CarRepository;
import com.example.carsharing.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarDbService {


    private final CarRepository carRepository;

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    public Car getCarById(Long id) {
        return carRepository.findById(id).orElseThrow(() -> new CarNotFoundException(id));
    }

    public Car saveCar(Car car) {
        return carRepository.save(car);
    }

    public void deleteCar(Long id) {
        carRepository.deleteById(id);
    }

    public Car updateCar(Long id, Car updatedCar) {
        Car existingCar = getCarById(id);

        existingCar.setMark(updatedCar.getMark());
        existingCar.setModel(updatedCar.getModel());
        existingCar.setMotorCapacity(updatedCar.getMotorCapacity());
        existingCar.setFuel(updatedCar.getFuel());

        return saveCar(existingCar);
    }
}