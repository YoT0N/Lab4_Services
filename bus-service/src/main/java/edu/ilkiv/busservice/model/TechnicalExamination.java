package edu.ilkiv.busservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "technical_examinations")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TechnicalExamination {

    @Id
    private String id;

    private String busId;  // Референс на ID автобуса

    private String busCountryNumber;  // державний номер автобуса для читабельності

    private LocalDate date;  // дата

    private String examinationResult;  // результат огляду

    private Boolean sentForRepair;  // відправлений на ремонт чи ні

    private Double repairPrice;  // ціна ремонту
}