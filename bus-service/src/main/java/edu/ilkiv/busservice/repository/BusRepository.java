package edu.ilkiv.busservice.repository;

import edu.ilkiv.busservice.model.Bus;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BusRepository extends MongoRepository<Bus, String> {
    Optional<Bus> findByCountryNumber(String countryNumber);
    Optional<Bus> findByBoardingNumber(String boardingNumber);
}