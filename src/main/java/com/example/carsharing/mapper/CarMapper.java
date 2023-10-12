package com.example.carsharing.mapper;

import com.example.carsharing.domain.Car;
import com.example.carsharing.domain.CarDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarMapper {

    public Car mapCarDtoToCar(CarDto carDto) {
        return Car.builder()
                .id(carDto.getId())
                .model(carDto.getModel())
                .mark(carDto.getMark())
                .motorCapacity(carDto.getMotorCapacity())
                .fuel(carDto.getFuel())
                .build();
    }

    public CarDto mapCarToCarDto(Car car) {
        return CarDto.builder()
                .id(car.getId())
                .mark(car.getMark())
                .model(car.getModel())
                .motorCapacity(car.getMotorCapacity())
                .fuel(car.getFuel())
                .build();
    }

    public List<CarDto> mapToCarDtoList(List<Car>cars) {
        return cars.stream()
                .map(this::mapCarToCarDto)
                .collect(Collectors.toList());

    }

}
