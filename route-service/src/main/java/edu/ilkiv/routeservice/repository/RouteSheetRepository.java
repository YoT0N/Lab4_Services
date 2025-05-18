package edu.ilkiv.routeservice.repository;

import edu.ilkiv.routeservice.model.RouteSheet;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RouteSheetRepository extends MongoRepository<RouteSheet, String> {
    List<RouteSheet> findByRouteId(String routeId);
    List<RouteSheet> findByBusId(String busId);
    List<RouteSheet> findByDriverId(String driverId);
    List<RouteSheet> findByDriverTabelNumber(String driverTabelNumber);
    List<RouteSheet> findByConductorId(String conductorId);
    List<RouteSheet> findByConductorTabelNumber(String conductorTabelNumber);
    List<RouteSheet> findByDate(LocalDate date);
}