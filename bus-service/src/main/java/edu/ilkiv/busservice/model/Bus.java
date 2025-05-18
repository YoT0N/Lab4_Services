package edu.ilkiv.busservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "buses")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bus {

    @Id
    private String id;

    @Indexed(unique = true)
    private String countryNumber;  // державний номер

    @Indexed(unique = true)
    private String boardingNumber;  // бортовий номер

    private String brand;  // марка

    private Integer passengerCapacity;  // місць для пасажирів

    private Integer yearOfManufacture;  // рік випуску

    private Double mileage;  // пробіг

    private LocalDate dateOfReceipt;  // дата отримання

    private LocalDate writeoffDate;  // дата списання
}