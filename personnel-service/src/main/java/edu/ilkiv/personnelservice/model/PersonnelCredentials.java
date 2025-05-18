package edu.ilkiv.personnelservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "personnel_credentials")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonnelCredentials {

    @Id
    private String id;

    @Indexed(unique = true)
    private String tabelNumber;  // табельний номер

    private String personalDataId;  // ID персональних даних

    private String position;  // посада

    private LocalDate dateOfEmployment;  // дата працевлаштування
}