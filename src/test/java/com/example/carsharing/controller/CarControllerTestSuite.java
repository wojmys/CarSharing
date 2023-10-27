package com.example.carsharing.controller;

import com.example.carsharing.domain.Car;
import com.example.carsharing.domain.CarDto;
import com.example.carsharing.mapper.CarMapper;
import com.example.carsharing.service.CarDbService;
import com.google.gson.Gson;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringJUnitWebConfig
@WebMvcTest(CarController.class)
class CarControllerTestSuite {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CarDbService carDbService;
    @MockBean
    private CarMapper carMapper;

    @Test
    void shouldCreateCar() throws Exception {
        //given
        Car car = Car.builder()
                .id(1L)
                .mark("Hyundai")
                .model("Getz")
                .build();
        CarDto carDto = CarDto.builder()
                .id(1L)
                .mark("Hyundai")
                .model("Getz")
                .build();
        when(carMapper.mapCarDtoToCar(carDto)).thenReturn(car);
        when(carDbService.saveCar(car)).thenReturn(car);
        String jsonContent = new Gson().toJson(carDto);

        //when& then
        mockMvc.perform(post("/api/cars")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(status().isOk());
    }

    @Test
    void shouldFetchEmptyCarList() throws Exception {
        //given
        when(carDbService.getAllCars()).thenReturn(List.of());
        //when & then
        mockMvc.perform(get("/api/cars")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200)) // or isOk()
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(0)));
    }

    @Test
    void shouldGetAllCars() throws Exception {
        //given
        List<Car> listOfCars = List.of(
                (Car.builder()
                        .id(1L)
                        .mark("Hyundai")
                        .model("Getz")
                        .build()),
                (Car.builder()
                        .id(2L)
                        .mark("Mercedes")
                        .model("A160")
                        .build())
        );
        List<CarDto> listOfCarDtos = List.of(
                (CarDto.builder()
                        .id(1L)
                        .mark("Hyundai")
                        .model("Getz")
                        .build()),
                (CarDto.builder()
                        .id(2L)
                        .mark("Mercedes")
                        .model("A160")
                        .build())
        );
        when(carDbService.getAllCars()).thenReturn(listOfCars);
        when(carMapper.mapToCarDtoList(listOfCars)).thenReturn(listOfCarDtos);
        //when & then
        mockMvc.perform(get("/api/cars")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].mark", Matchers.is("Hyundai")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].model", Matchers.is("Getz")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id", Matchers.is(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].mark", Matchers.is("Mercedes")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].model", Matchers.is("A160")));
    }

    @Test
    void shouldGetCar() throws Exception {
        //given
        Car car = Car.builder()
                .id(2L)
                .mark("Mercedes")
                .model("A160")
                .build();
        CarDto carDto = CarDto.builder()
                .id(2L)
                .mark("Mercedes")
                .model("A160")
                .build();
        when(carDbService.getCarById(2L)).thenReturn(car);
        when(carMapper.mapCarToCarDto(car)).thenReturn(carDto);
        //when & then
        mockMvc.perform(get("/api/cars/{carId}", carDto.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id",Matchers.is(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.mark",Matchers.is("Mercedes")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.model",Matchers.is("A160")));
    }
    @Test
    void shouldUpdateCar() throws Exception {
        //given
        Car car = Car.builder()
                .id(2L)
                .mark("Mercedes")
                .model("A160")
                .build();
        CarDto requestedCarDto = CarDto.builder()
                .id(2L)
                .mark("Mercedes")
                .model("B180")
                .build();
        when(carMapper.mapCarDtoToCar(any())).thenReturn(car);
        when(carDbService.saveCar(any())).thenReturn(car);
        when(carMapper.mapCarToCarDto(any())).thenReturn(requestedCarDto);
        String jsonContent = new Gson().toJson(requestedCarDto);
        //When & Then
        mockMvc.perform(put("/api/cars/{carId}",requestedCarDto.getId() )
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.model", Matchers.is("B180")));
    }
}
