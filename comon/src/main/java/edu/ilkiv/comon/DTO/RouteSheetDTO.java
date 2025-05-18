package edu.ilkiv.comon.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RouteSheetDTO {
    private String id;
    private String routeId;
    private String routeNumber;
    private String busId;
    private String busCountryNumber;
    private LocalDate date;
    private Integer trips;
    private String driverId;
    private String driverTabelNumber;
    private String conductorId;
    private String conductorTabelNumber;
    private Integer totalPassengers;
}