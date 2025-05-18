package edu.ilkiv.comon.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RouteDTO {
    private String id;
    private String routeNumber;
    private String routeName;
    private Double routeLength;
    private Integer averageTime;
    private Integer plannedTripsPerShift;
}