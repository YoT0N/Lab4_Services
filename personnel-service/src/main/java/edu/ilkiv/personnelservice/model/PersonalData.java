package edu.ilkiv.personnelservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "personal_data")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonalData {

    @Id
    private String id;

    @Indexed(unique = true)
    private String tabelNumber;  // табельний номер

    private String fullName;  // повне ім'я

    private LocalDate dateOfBirth;  // дата народження

    private String sex;  // стать

    private String homeAddress;  // домашній адрес

    private String homePhone;  // домашній телефон

    private String phoneNumber;  // робочий телефон
}