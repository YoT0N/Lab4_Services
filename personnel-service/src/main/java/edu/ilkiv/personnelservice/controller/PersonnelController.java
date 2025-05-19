package edu.ilkiv.personnelservice.controller;

import edu.ilkiv.comon.DTO.PersonalDataDTO;
import edu.ilkiv.comon.DTO.PersonnelCredentialsDTO;
import edu.ilkiv.comon.DTO.PhysicalExaminationDTO;
import edu.ilkiv.comon.DTO.StaffWithRouteResponse;
import edu.ilkiv.personnelservice.service.PersonnelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/personnel")
public class PersonnelController {

    private final PersonnelService personnelService;

    @Autowired
    public PersonnelController(PersonnelService personnelService) {
        this.personnelService = personnelService;
    }

    @GetMapping
    public ResponseEntity<List<PersonalDataDTO>> getAllPersonnel() {
        List<PersonalDataDTO> personnel = personnelService.getAllPersonnel();
        return new ResponseEntity<>(personnel, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonalDataDTO> getPersonnelById(@PathVariable String id) {
        PersonalDataDTO personalData = personnelService.getPersonnelById(id);
        if (personalData != null) {
            return new ResponseEntity<>(personalData, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/tabel/{tabelNumber}")
    public ResponseEntity<PersonalDataDTO> getPersonnelByTabelNumber(@PathVariable String tabelNumber) {
        PersonalDataDTO personalData = personnelService.getPersonnelByTabelNumber(tabelNumber);
        if (personalData != null) {
            return new ResponseEntity<>(personalData, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<PersonalDataDTO> createPersonalData(@RequestBody PersonalDataDTO personalDataDTO) {
        PersonalDataDTO savedPersonalData = personnelService.savePersonalData(personalDataDTO);
        return new ResponseEntity<>(savedPersonalData, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonalDataDTO> updatePersonalData(@PathVariable String id, @RequestBody PersonalDataDTO personalDataDTO) {
        personalDataDTO.setId(id);
        PersonalDataDTO updatedPersonalData = personnelService.savePersonalData(personalDataDTO);
        return new ResponseEntity<>(updatedPersonalData, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePersonnel(@PathVariable String id) {
        personnelService.deletePersonnel(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/credentials")
    public ResponseEntity<PersonnelCredentialsDTO> createCredentials(@RequestBody PersonnelCredentialsDTO credentialsDTO) {
        PersonnelCredentialsDTO savedCredentials = personnelService.saveCredentials(credentialsDTO);
        return new ResponseEntity<>(savedCredentials, HttpStatus.CREATED);
    }

    @PostMapping("/examinations")
    public ResponseEntity<PhysicalExaminationDTO> createExamination(@RequestBody PhysicalExaminationDTO examinationDTO) {
        PhysicalExaminationDTO savedExamination = personnelService.saveExamination(examinationDTO);
        return new ResponseEntity<>(savedExamination, HttpStatus.CREATED);
    }

    @GetMapping("/staff-routes/{tabelNumber}")
    public ResponseEntity<StaffWithRouteResponse> getStaffWithRoutes(@PathVariable String tabelNumber) {
        StaffWithRouteResponse response = personnelService.getStaffWithRoutes(tabelNumber);
        if (response != null) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}