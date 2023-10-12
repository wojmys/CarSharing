package com.example.carsharing.repository;

import com.example.carsharing.domain.Car;
import jakarta.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface CarRepository extends CrudRepository<Car, Long > {

    List<Car> findAll();
    Optional<Car> findById(Long id);
    void deleteById(Long id);
}
