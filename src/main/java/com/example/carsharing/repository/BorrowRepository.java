package com.example.carsharing.repository;

import com.example.carsharing.domain.Borrow;
import com.example.carsharing.domain.Car;
import jakarta.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface BorrowRepository extends CrudRepository<Borrow, Long> {
    List<Borrow> findAll();
    Optional<Borrow> findById(Long id);
    void deleteById(Long id);


}
