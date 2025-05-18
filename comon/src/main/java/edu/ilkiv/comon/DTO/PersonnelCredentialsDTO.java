package edu.ilkiv.comon.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonnelCredentialsDTO {
    private String id;
    private String tabelNumber;
    private String personalDataId;
    private String position;
    private LocalDate dateOfEmployment;
}