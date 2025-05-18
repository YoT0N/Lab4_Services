package edu.ilkiv.comon.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PhysicalExaminationDTO {
    private String id;
    private String personnelId;
    private String tabelNumber;
    private LocalDate date;
    private String examinationResult;
}