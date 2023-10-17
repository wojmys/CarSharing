package com.example.carsharing.repository;

import com.example.carsharing.domain.Rent;
import jakarta.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface RentRepository extends CrudRepository<Rent, Long> {

    List<Rent> findAll();
    Optional<Rent> findById(Long id);
    void deleteById(Long id);
}
