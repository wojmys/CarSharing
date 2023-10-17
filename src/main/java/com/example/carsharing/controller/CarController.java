package com.example.carsharing.controller;

import com.example.carsharing.domain.Car;
import com.example.carsharing.domain.CarDto;
import com.example.carsharing.domain.User;
import com.example.carsharing.domain.UserDto;
import com.example.carsharing.mapper.CarMapper;
import com.example.carsharing.service.CarDbService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/cars")
@Slf4j
@RequiredArgsConstructor
public class CarController {

    private final CarMapper carMapper;
    private final CarDbService carDbService;

    @GetMapping
    public ResponseEntity<List<CarDto>>fetchAllCars(){
        log.info("Fetching all cars...");
        List<Car>cars = carDbService.getAllCars();
        log.info("Successfully fetched");
        return ResponseEntity.ok(carMapper.mapToCarDtoList(cars));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarDto>fetchCarById(@PathVariable Long id){
        log.info("Fetching Car with id="+id);
        Car car = carDbService.getCarById(id);
        log.info("Successfully fetched");
        return ResponseEntity.ok(carMapper.mapCarToCarDto(car));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void>createCar(@RequestBody CarDto carDto){
        log.info("Creating Car...");
        carDbService.saveCar(carMapper.mapCarDtoToCar(carDto));
        log.info("Successfully created!");
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void>deleteCar(@PathVariable Long id){
        log.info("Deleting Car");
        carDbService.deleteCar(id);
        log.info("Car with id="+id+" successfully deleted!");
        return ResponseEntity.ok().build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<CarDto>editCar(@PathVariable Long id, @RequestBody CarDto carDto){
        log.info("Editing Car with id="+id);
        Car updatedCar = carMapper.mapCarDtoToCar(carDto);
        Car savedCar = carDbService.updateCar(id, updatedCar);
        log.info("Car with id="+id+" successfully updated!");
        return ResponseEntity.ok(carMapper.mapCarToCarDto(savedCar));
    }

}
