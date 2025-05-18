package edu.ilkiv.routeservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "route_sheets")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RouteSheet {

    @Id
    private String id;

    private String routeId;  // ID маршруту
    private String routeNumber;  // номер маршруту для читабельності

    private String busId;  // ID автобуса
    private String busCountryNumber;  // державний номер автобуса для читабельності

    private LocalDate date;  // дата

    private Integer trips;  // рейси

    private String driverId;  // ID водія
    private String driverTabelNumber;  // табельний номер водія для читабельності

    private String conductorId;  // ID кондуктора
    private String conductorTabelNumber;  // табельний номер кондуктора для читабельності

    private Integer totalPassengers;  // кількість пасажирів за зміну
}