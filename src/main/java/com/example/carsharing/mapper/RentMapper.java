package com.example.carsharing.mapper;

import com.example.carsharing.domain.Rent;
import com.example.carsharing.domain.RentDto;
import com.example.carsharing.service.CarDbService;
import com.example.carsharing.service.UserDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RentMapper {

    @Autowired
    CarMapper carMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    CarDbService carDbService;
    @Autowired
    UserDbService userDbService;

    public RentDto mapRentToRentDto(Rent rent) {
        return RentDto.builder()
                .id(rent.getId())
                .userId(rent.getId())
                .carId(rent.getId())
                .rentDate(rent.getBorrowDate())
                .returnDate(rent.getReturnDate())
                .distanceTravelledInKm(rent.getDistanceTravelledInKm())
                .totalFuelCost(rent.getTotalFuelCost())
                .status(rent.getStatus())

                .build();

    }

    public Rent mapRentDtoToRent(RentDto rentDto) {
        return Rent.builder()
                .id(rentDto.getId())
                .car(carDbService.getCarById(rentDto.getId()))
                .user(userDbService.getUserById(rentDto.getCarId()))
                .borrowDate(rentDto.getRentDate())
                .returnDate(rentDto.getReturnDate())
                .distanceTravelledInKm(rentDto.getDistanceTravelledInKm())
                .totalFuelCost(rentDto.getTotalFuelCost())
                .status(rentDto.getStatus())

                .build();

    }

    public List<RentDto> mapToRentDtoList(List<Rent> rentList) {
        return rentList.stream()
                .map(this::mapRentToRentDto)
                .collect(Collectors.toList());
    }
}
