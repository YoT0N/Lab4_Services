package edu.ilkiv.comon.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonalDataDTO {
    private String id;
    private String tabelNumber;
    private String fullName;
    private LocalDate dateOfBirth;
    private String sex;
    private String homeAddress;
    private String homePhone;
    private String phoneNumber;
}