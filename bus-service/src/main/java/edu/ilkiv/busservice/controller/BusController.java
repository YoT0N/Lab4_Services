package edu.ilkiv.busservice.controller;

import edu.ilkiv.comon.DTO.BusDTO;
import edu.ilkiv.comon.DTO.BusWithExaminationsResponse;
import edu.ilkiv.comon.DTO.TechnicalExaminationDTO;
import edu.ilkiv.busservice.service.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/buses")
public class BusController {

    private final BusService busService;

    @Autowired
    public BusController(BusService busService) {
        this.busService = busService;
    }

    @GetMapping
    public ResponseEntity<List<BusDTO>> getAllBuses() {
        List<BusDTO> buses = busService.getAllBuses();
        return new ResponseEntity<>(buses, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BusDTO> getBusById(@PathVariable String id) {
        BusDTO bus = busService.getBusById(id);
        if (bus != null) {
            return new ResponseEntity<>(bus, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/country-number/{countryNumber}")
    public ResponseEntity<BusDTO> getBusByCountryNumber(@PathVariable String countryNumber) {
        BusDTO bus = busService.getBusByCountryNumber(countryNumber);
        if (bus != null) {
            return new ResponseEntity<>(bus, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<BusDTO> createBus(@RequestBody BusDTO busDTO) {
        BusDTO savedBus = busService.saveBus(busDTO);
        return new ResponseEntity<>(savedBus, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BusDTO> updateBus(@PathVariable String id, @RequestBody BusDTO busDTO) {
        busDTO.setId(id);
        BusDTO updatedBus = busService.saveBus(busDTO);
        return new ResponseEntity<>(updatedBus, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBus(@PathVariable String id) {
        busService.deleteBus(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}/examinations")
    public ResponseEntity<BusWithExaminationsResponse> getBusWithExaminations(@PathVariable String id) {
        BusWithExaminationsResponse response = busService.getBusWithExaminations(id);
        if (response != null) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/examinations")
    public ResponseEntity<TechnicalExaminationDTO> createExamination(@RequestBody TechnicalExaminationDTO examinationDTO) {
        TechnicalExaminationDTO savedExamination = busService.saveExamination(examinationDTO);
        return new ResponseEntity<>(savedExamination, HttpStatus.CREATED);
    }
}