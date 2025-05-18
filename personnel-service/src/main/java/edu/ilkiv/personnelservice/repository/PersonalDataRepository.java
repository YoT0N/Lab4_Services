package edu.ilkiv.personnelservice.repository;

import edu.ilkiv.personnelservice.model.PersonalData;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonalDataRepository extends MongoRepository<PersonalData, String> {
    Optional<PersonalData> findByTabelNumber(String tabelNumber);
}