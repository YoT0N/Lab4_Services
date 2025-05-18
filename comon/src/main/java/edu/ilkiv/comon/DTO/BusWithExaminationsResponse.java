package edu.ilkiv.comon.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BusWithExaminationsResponse {
    private BusDTO bus;
    private List<TechnicalExaminationDTO> examinations;
}