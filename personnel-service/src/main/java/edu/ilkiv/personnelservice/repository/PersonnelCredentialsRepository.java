package edu.ilkiv.personnelservice.repository;

import edu.ilkiv.personnelservice.model.PersonnelCredentials;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonnelCredentialsRepository extends MongoRepository<PersonnelCredentials, String> {
    Optional<PersonnelCredentials> findByTabelNumber(String tabelNumber);
    Optional<PersonnelCredentials> findByPersonalDataId(String personalDataId);
}