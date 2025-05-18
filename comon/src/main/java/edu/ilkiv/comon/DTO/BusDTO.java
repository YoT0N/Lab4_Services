package edu.ilkiv.comon.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BusDTO {
    private String id;
    private String countryNumber;
    private String boardingNumber;
    private String brand;
    private Integer passengerCapacity;
    private Integer yearOfManufacture;
    private Double mileage;
    private LocalDate dateOfReceipt;
    private LocalDate writeoffDate;
}