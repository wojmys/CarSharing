package com.example.carsharing.repository;

import com.example.carsharing.domain.Rent;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface RentRepository extends CrudRepository<Rent, Long> {

    List<Rent> findAll();
    Optional<Rent> findById(Long id);
    void deleteById(Long id);

//    @Query("SELECT COUNT(r) FROM Rent r WHERE r.rentDate >= :startDate AND r.returnDate <= :endDate AND r.car.id = :carId")
//    Long countRentsInDateRangeForCar(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate, @Param("carId") Long carId);

    @Query("SELECT COUNT(r) FROM Rent r WHERE (r.rentDate BETWEEN :startDate AND :endDate AND r.car.id = :carId) OR (r.returnDate BETWEEN :startDate AND :endDate AND r.car.id = :carId)")
    Long countRentsInDateRangeForCar(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate, @Param("carId") Long carId);
}

