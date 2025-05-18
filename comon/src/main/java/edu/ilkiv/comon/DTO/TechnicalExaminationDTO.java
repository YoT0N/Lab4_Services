package edu.ilkiv.comon.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TechnicalExaminationDTO {
    private String id;
    private String busId;
    private String busCountryNumber;
    private LocalDate date;
    private String examinationResult;
    private Boolean sentForRepair;
    private Double repairPrice;
}