package edu.ilkiv.routeservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "routes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Route {

    @Id
    private String id;

    @Indexed(unique = true)
    private String routeNumber;  // номер маршруту

    private String routeName;  // назва маршруту

    private Double routeLength;  // довжина маршруту

    private Integer averageTime;  // середній час рейсу (у хвилинах)

    private Integer plannedTripsPerShift;  // заплановані рейси за зміну
}