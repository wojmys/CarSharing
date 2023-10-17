package com.example.carsharing.service;

import com.example.carsharing.domain.Rent;
import com.example.carsharing.error.rent.RentNotFoundException;
import com.example.carsharing.repository.RentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RentDbService {

    private final RentRepository rentRepository;

    public List<Rent> getAllRent() {
        return rentRepository.findAll();
    }

    public Rent getRentById(Long id) {
        return rentRepository.findById(id).orElseThrow(() -> new RentNotFoundException(id));
    }

    public Rent saveRent(Rent rent) {
        return rentRepository.save(rent);
    }

    public void deleteRent(Long id) {
        rentRepository.deleteById(id);
    }

    public Rent updateRent(Long id, Rent updatedRent) {
        Rent existingRent = getRentById(id);

        existingRent.setUser(updatedRent.getUser());
        existingRent.setCar(updatedRent.getCar());
        existingRent.setRentDate(updatedRent.getRentDate());
        existingRent.setReturnDate(updatedRent.getReturnDate());
        existingRent.setDistanceTravelledInKm(updatedRent.getDistanceTravelledInKm());
        existingRent.setTotalFuelCost(updatedRent.getTotalFuelCost());

        return saveRent(existingRent);
    }

}
