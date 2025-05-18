package edu.ilkiv.personnelservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "physical_examinations")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PhysicalExamination {

    @Id
    private String id;

    private String personnelId;  // ID співробітника

    private String tabelNumber;  // табельний номер для читабельності

    private LocalDate date;  // дата

    private String examinationResult;  // результат огляду
}