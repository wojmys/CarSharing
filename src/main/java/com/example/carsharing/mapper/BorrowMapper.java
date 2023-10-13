package com.example.carsharing.mapper;

import com.example.carsharing.domain.Borrow;
import com.example.carsharing.domain.BorrowDto;
import com.example.carsharing.service.CarDbService;
import com.example.carsharing.service.UserDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BorrowMapper {

    @Autowired
    CarMapper carMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    CarDbService carDbService;
    @Autowired
    UserDbService userDbService;

    public BorrowDto mapBorrowToBorrowDto(Borrow borrow) {
        return BorrowDto.builder()
                .id(borrow.getId())
                .userId(borrow.getId())
                .carId(borrow.getId())
                .borrowDate(borrow.getBorrowDate())
                .returnDate(borrow.getReturnDate())
                .distanceTravelledInKm(borrow.getDistanceTravelledInKm())
                .totalFuelCost(borrow.getTotalFuelCost())
                .status(borrow.getStatus())

                .build();

    }

    public Borrow mapBorrowDtoToBorrow(BorrowDto borrowDto) {
        return Borrow.builder()
                .id(borrowDto.getId())
                .car(carDbService.getCarById(borrowDto.getId()))
                .user(userDbService.getUserById(borrowDto.getCarId()))
                .borrowDate(borrowDto.getBorrowDate())
                .returnDate(borrowDto.getReturnDate())
                .distanceTravelledInKm(borrowDto.getDistanceTravelledInKm())
                .totalFuelCost(borrowDto.getTotalFuelCost())
                .status(borrowDto.getStatus())

                .build();

    }

    public List<BorrowDto> mapToBorrowDtoList(List<Borrow> borrowList) {
        return borrowList.stream()
                .map(this::mapBorrowToBorrowDto)
                .collect(Collectors.toList());
    }

}
