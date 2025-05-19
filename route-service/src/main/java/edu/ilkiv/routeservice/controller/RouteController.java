package edu.ilkiv.routeservice.controller;

import edu.ilkiv.comon.DTO.RouteDTO;
import edu.ilkiv.comon.DTO.RouteSheetDTO;
import edu.ilkiv.routeservice.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api")
public class RouteController {

    private final RouteService routeService;

    @Autowired
    public RouteController(RouteService routeService) {
        this.routeService = routeService;
    }

    // Route endpoints
    @GetMapping("/routes")
    public ResponseEntity<List<RouteDTO>> getAllRoutes() {
        List<RouteDTO> routes = routeService.getAllRoutes();
        return new ResponseEntity<>(routes, HttpStatus.OK);
    }

    @GetMapping("/routes/{id}")
    public ResponseEntity<RouteDTO> getRouteById(@PathVariable String id) {
        RouteDTO route = routeService.getRouteById(id);
        if (route != null) {
            return new ResponseEntity<>(route, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/routes/number/{routeNumber}")
    public ResponseEntity<RouteDTO> getRouteByNumber(@PathVariable String routeNumber) {
        RouteDTO route = routeService.getRouteByNumber(routeNumber);
        if (route != null) {
            return new ResponseEntity<>(route, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/routes")
    public ResponseEntity<RouteDTO> createRoute(@RequestBody RouteDTO routeDTO) {
        RouteDTO savedRoute = routeService.saveRoute(routeDTO);
        return new ResponseEntity<>(savedRoute, HttpStatus.CREATED);
    }

    @PutMapping("/routes/{id}")
    public ResponseEntity<RouteDTO> updateRoute(@PathVariable String id, @RequestBody RouteDTO routeDTO) {
        routeDTO.setId(id);
        RouteDTO updatedRoute = routeService.saveRoute(routeDTO);
        return new ResponseEntity<>(updatedRoute, HttpStatus.OK);
    }

    @DeleteMapping("/routes/{id}")
    public ResponseEntity<Void> deleteRoute(@PathVariable String id) {
        routeService.deleteRoute(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Route Sheet endpoints
    @GetMapping("/route-sheets/route/{routeId}")
    public ResponseEntity<List<RouteSheetDTO>> getRouteSheetsByRouteId(@PathVariable String routeId) {
        List<RouteSheetDTO> routeSheets = routeService.getRouteSheetsByRouteId(routeId);
        return new ResponseEntity<>(routeSheets, HttpStatus.OK);
    }

    @GetMapping("/route-sheets/driver/{tabelNumber}")
    public ResponseEntity<List<RouteSheetDTO>> getRouteSheetsByDriverTabelNumber(@PathVariable String tabelNumber) {
        List<RouteSheetDTO> routeSheets = routeService.getRouteSheetsByDriverTabelNumber(tabelNumber);
        return new ResponseEntity<>(routeSheets, HttpStatus.OK);
    }

    @GetMapping("/route-sheets/conductor/{tabelNumber}")
    public ResponseEntity<List<RouteSheetDTO>> getRouteSheetsByConductorTabelNumber(@PathVariable String tabelNumber) {
        List<RouteSheetDTO> routeSheets = routeService.getRouteSheetsByConductorTabelNumber(tabelNumber);
        return new ResponseEntity<>(routeSheets, HttpStatus.OK);
    }

    @GetMapping("/route-sheets/date/{date}")
    public ResponseEntity<List<RouteSheetDTO>> getRouteSheetsByDate(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        List<RouteSheetDTO> routeSheets = routeService.getRouteSheetsByDate(date);
        return new ResponseEntity<>(routeSheets, HttpStatus.OK);
    }

    @PostMapping("/route-sheets")
    public ResponseEntity<RouteSheetDTO> createRouteSheet(@RequestBody RouteSheetDTO routeSheetDTO) {
        try {
            RouteSheetDTO savedRouteSheet = routeService.saveRouteSheet(routeSheetDTO);
            return new ResponseEntity<>(savedRouteSheet, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}