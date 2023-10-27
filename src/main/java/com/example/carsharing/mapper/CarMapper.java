package com.example.carsharing.mapper;

import com.example.carsharing.domain.Car;
import com.example.carsharing.domain.CarDto;
import com.example.carsharing.domain.Rent;
import com.example.carsharing.service.RentDbService;
import org.springframework.beans.factory.annotation.Autowired;
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
                .status(carDto.getStatus())
                .rentList(carDto.getRentIds().stream()
                        .map(rentId -> {
                            Rent rent = new Rent();
                            rent.setId(rentId);
                            return rent;
                        })
                        .collect(Collectors.toList()))
                .build();
    }

    public CarDto mapCarToCarDto(Car car) {
        return CarDto.builder()
                .id(car.getId())
                .mark(car.getMark())
                .model(car.getModel())
                .motorCapacity(car.getMotorCapacity())
                .fuel(car.getFuel())
                .status(car.getStatus())
                .build();
    }

    public List<CarDto> mapToCarDtoList(List<Car>cars) {
        return cars.stream()
                .map(this::mapCarToCarDto)
                .collect(Collectors.toList());
    }
}
