package edu.ilkiv.busservice.repository;

import edu.ilkiv.busservice.model.TechnicalExamination;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TechnicalExaminationRepository extends MongoRepository<TechnicalExamination, String> {
    List<TechnicalExamination> findByBusId(String busId);
    List<TechnicalExamination> findByBusCountryNumber(String busCountryNumber);
}