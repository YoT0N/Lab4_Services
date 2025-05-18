package edu.ilkiv.routeservice.repository;

import edu.ilkiv.routeservice.model.Route;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RouteRepository extends MongoRepository<Route, String> {
    Optional<Route> findByRouteNumber(String routeNumber);
}