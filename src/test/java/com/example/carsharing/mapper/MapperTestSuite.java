package com.example.carsharing.mapper;

import com.example.carsharing.domain.*;
import com.example.carsharing.service.CarDbService;
import com.example.carsharing.service.RentDbService;
import com.example.carsharing.service.UserDbService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest
public class MapperTestSuite {

    @Autowired
    private CarMapper carMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RentMapper rentMapper;
    @Autowired
    private RentDbService rentDbService;
    @Autowired
    private CarDbService carDbService;
    @Autowired
    private UserDbService userDbService;

    @Test
    void mapCarToCarDtoTest() {
        //given
        Car car = Car.builder()
                .id(1L)
                .mark("Vw")
                .model("Golf")
                .motorCapacity(1.4)
                .fuel(Fuel.LPG)
                .status(Status.AVAILABLE)
                .build();
        //when
        CarDto carDto = carMapper.mapCarToCarDto(car);
        //then
        assertNotNull(carDto);
        assertEquals(1L, carDto.getId());
        assertEquals("Vw", carDto.getMark());
        assertEquals("Golf", carDto.getModel());
        assertEquals(Status.AVAILABLE,carDto.getStatus());
        assertEquals(1.4, carDto.getMotorCapacity());
    }

    @Test
    void mapCarDtoToCarTest() {
        CarDto carDto = CarDto.builder()
                .id(1L)
                .mark("Vw")
                .model("Golf")
                .motorCapacity(1.4)
                .fuel(Fuel.DIESEL)
                .status(Status.AVAILABLE)
                .rentIds(List.of())
                .build();
        //when
        Car car = carMapper.mapCarDtoToCar(carDto);
        //then
        assertNotNull(car);
        assertEquals(1L, car.getId());
        assertEquals("Vw", car.getMark());
        assertEquals("Golf", car.getModel());
        assertEquals(Fuel.DIESEL,car.getFuel());
        assertEquals(1.4, car.getMotorCapacity());
    }

    @Test
    void mapToCarDtoListTest() {
        //given
        Car firstCar = Car.builder()
                .id(1L)
                .mark("Vw")
                .model("Golf")
                .motorCapacity(1.4)
                .build();
        Car secondCar = Car.builder()
                .id(2L)
                .mark("Vw")
                .model("Polo")
                .motorCapacity(1.2)
                .build();
        List<Car> listOfCars = new ArrayList<>();
        listOfCars.add(firstCar);
        listOfCars.add(secondCar);
        //then
        List<CarDto> listOfDtoCars = carMapper.mapToCarDtoList(listOfCars);
        //then
        assertEquals(2, listOfDtoCars.size());
    }

    @Test
    void mapUserToUserDtoTest() {
        //given
        User user = User.builder()
                .id(5L)
                .isTopCustomer(true)
                .name("John Smith")
                .rentList(new ArrayList<>())
                .build();
        //when
        UserDto userDto = userMapper.mapUserToUserDto(user);
        //then
        assertNotNull(userDto);
        assertEquals(5L, userDto.getId());
    }

    @Test
    void mapUserDtoToUserTest() {
        //given
        UserDto userDto = UserDto.builder()
                .isTopCustomer(false)
                .name("John John")
                .id(10L)
                .rentIds(new ArrayList<>())
                .build();
        //when
        User user = userMapper.mapUserDtoToUser(userDto);
        //then
        assertNotNull(user);
        assertEquals("John John", user.getName());
    }

    @Test
    void mapToUserDtoListTest() {
        //given
        User userJohn = User.builder()
                .id(5L)
                .isTopCustomer(true)
                .name("John Smith")
                .rentList(new ArrayList<>())
                .build();
        User userAlan = User.builder()
                .id(1L)
                .isTopCustomer(false)
                .name("Alan Kox")
                .rentList(new ArrayList<>())
                .build();
        List<User> listOfUsers = new ArrayList<>();
        listOfUsers.add(userJohn);
        listOfUsers.add(userAlan);
        //when
        List<UserDto> listOfUserDtos = userMapper.mapToUserDtoList(listOfUsers);
        //then
        assertEquals(listOfUserDtos.size(),2);
    }

    @Test
    void mapRentToRentDtoTest(){
        //given
        User user = User.builder()
                .id(5L)
                .isTopCustomer(true)
                .name("John Smith")
                .rentList(new ArrayList<>())
                .build();
        Car car = Car.builder()
                .id(2L)
                .mark("Vw")
                .model("Golf")
                .motorCapacity(1.4)
                .build();
        Rent rent = Rent.builder()
                .id(1L)
                .user(user)
                .car(car)
                .rentDate(LocalDate.of(2023,01,01))
                .returnDate(LocalDate.of(2023,01,10))
                .build();
        //when
        RentDto rentDto = rentMapper.mapRentToRentDto(rent);
        //then
        assertNotNull(rentDto);
        assertEquals(1L,rentDto.getId());
        assertEquals(2L,rentDto.getCarId());
        assertEquals(5L,rentDto.getUserId());
    }
}
