package edu.ilkiv.personnelservice.repository;

import edu.ilkiv.personnelservice.model.PhysicalExamination;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhysicalExaminationRepository extends MongoRepository<PhysicalExamination, String> {
    List<PhysicalExamination> findByPersonnelId(String personnelId);
    List<PhysicalExamination> findByTabelNumber(String tabelNumber);
}