package com.example.carsharing.controller;


import com.example.carsharing.domain.CarDto;
import com.example.carsharing.domain.Rent;
import com.example.carsharing.domain.RentDto;
import com.example.carsharing.domain.User;
import com.example.carsharing.mapper.RentMapper;
import com.example.carsharing.service.CarDbService;
import com.example.carsharing.service.RentDbService;
import com.example.carsharing.service.UserDbService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/rents")
public class RentController {


//    private final CarDbService carDbService;
//    private final UserDbService userDbService;
    private final RentMapper rentMapper;
    private final RentDbService rentDbService;


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> rentCar(@RequestBody RentDto rentDto) {
        log.info("Booking car...");
        rentDbService.saveRent(rentMapper.mapRentDtoToRent(rentDto));
        log.info("Successfully booked!");
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/fuel/{rentId}")
    public ResponseEntity<Void> calculateFuelCostByRentId(@PathVariable Long rentId) {
        log.info("calculating fuel cost for Car with id=" + rentId);
        Rent rent = rentDbService.getRentById(rentId);
        rent.setTotalFuelCost(rent.calculateTotalFuelCost());
        rentDbService.saveRent(rent);
        log.info("Successfully calculated!");
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<RentDto>> fetchAllRents() {
        log.info("Fetching all RENTS");
        return ResponseEntity.ok(rentMapper.mapToRentDtoList(rentDbService.getAllRent()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RentDto> fetchRentById(@PathVariable Long id) {
        Rent rent = rentDbService.getRentById(id);
        return ResponseEntity.ok(rentMapper.mapRentToRentDto(rent));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRent(@PathVariable Long id) {
        log.info("deleting Rent with id="+id);
        rentDbService.deleteRent(id);
        log.info("Successfully deleted!");
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{rentId}")
    public ResponseEntity<RentDto> updateRent(@PathVariable Long rentId, @RequestBody RentDto updatedRentDto) {
        log.info("Updating Rent with id="+rentId);
        Rent rent = rentDbService.getRentById(rentId);
        rent.setDistanceTravelledInKm(updatedRentDto.getDistanceTravelledInKm());
        rentDbService.saveRent(rent);
        log.info("successfully updated!");
        return ResponseEntity.ok().build();
    }


}
