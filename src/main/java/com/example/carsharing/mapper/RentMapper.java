package com.example.carsharing.mapper;

import com.example.carsharing.domain.Car;
import com.example.carsharing.domain.Rent;
import com.example.carsharing.domain.RentDto;
import com.example.carsharing.domain.User;
import com.example.carsharing.service.CarDbService;
import com.example.carsharing.service.UserDbService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RentMapper {

    private CarDbService carDbService;
    private UserDbService userDbService;

    public RentDto mapRentToRentDto(Rent rent) {
        return RentDto.builder()
                .id(rent.getId())
                .userId(rent.getUser().getId())
                .carId(rent.getCar().getId())
                .rentDate(rent.getRentDate())
                .returnDate(rent.getReturnDate())
                .distanceTravelledInKm(rent.getDistanceTravelledInKm())
                .totalFuelCost(rent.getTotalFuelCost())

                .build();

    }

    public Rent mapRentDtoToRent(RentDto rentDto) {
        return Rent.builder()
                .id(rentDto.getId())
                .car(carDbService.getCarById(rentDto.getCarId()))
                .user(userDbService.getUserById(rentDto.getUserId()))
                .rentDate(rentDto.getRentDate())
                .returnDate(rentDto.getReturnDate())
                .distanceTravelledInKm(rentDto.getDistanceTravelledInKm())
                .totalFuelCost(rentDto.getTotalFuelCost())

                .build();
    }

    public List<RentDto> mapToRentDtoList(List<Rent> rentList) {
        return rentList.stream()
                .map(this::mapRentToRentDto)
                .collect(Collectors.toList());
    }
}
