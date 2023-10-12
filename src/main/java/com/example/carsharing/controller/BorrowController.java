package com.example.carsharing.controller;

import com.example.carsharing.domain.Car;
import com.example.carsharing.domain.Fuel;
import com.example.carsharing.service.CarDbService;
import com.example.carsharing.service.UserDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/borrow")
public class BorrowController {

    @Autowired
    private CarDbService carDbService;
    @Autowired
    private UserDbService userDbService;


}
