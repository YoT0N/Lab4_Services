package edu.ilkiv.personnelservice.service;

import edu.ilkiv.comon.DTO.*;
import edu.ilkiv.personnelservice.model.PersonalData;
import edu.ilkiv.personnelservice.model.PersonnelCredentials;
import edu.ilkiv.personnelservice.model.PhysicalExamination;
import edu.ilkiv.personnelservice.repository.PersonalDataRepository;
import edu.ilkiv.personnelservice.repository.PersonnelCredentialsRepository;
import edu.ilkiv.personnelservice.repository.PhysicalExaminationRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonnelService {

    private final PersonalDataRepository personalDataRepository;
    private final PersonnelCredentialsRepository personnelCredentialsRepository;
    private final PhysicalExaminationRepository physicalExaminationRepository;
    private final RestTemplate restTemplate;

    @Value("${route.service.url}")
    private String routeServiceUrl;

    @Autowired
    public PersonnelService(PersonalDataRepository personalDataRepository,
                            PersonnelCredentialsRepository personnelCredentialsRepository,
                            PhysicalExaminationRepository physicalExaminationRepository,
                            RestTemplate restTemplate) {
        this.personalDataRepository = personalDataRepository;
        this.personnelCredentialsRepository = personnelCredentialsRepository;
        this.physicalExaminationRepository = physicalExaminationRepository;
        this.restTemplate = restTemplate;
    }

    public List<PersonalDataDTO> getAllPersonnel() {
        return personalDataRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public PersonalDataDTO getPersonnelById(String id) {
        return personalDataRepository.findById(id)
                .map(this::convertToDTO)
                .orElse(null);
    }

    public PersonalDataDTO getPersonnelByTabelNumber(String tabelNumber) {
        return personalDataRepository.findByTabelNumber(tabelNumber)
                .map(this::convertToDTO)
                .orElse(null);
    }

    public PersonalDataDTO savePersonalData(PersonalDataDTO personalDataDTO) {
        PersonalData personalData = convertToEntity(personalDataDTO);
        return convertToDTO(personalDataRepository.save(personalData));
    }

    public PersonnelCredentialsDTO saveCredentials(PersonnelCredentialsDTO credentialsDTO) {
        PersonnelCredentials credentials = convertToCredentialsEntity(credentialsDTO);
        return convertToCredentialsDTO(personnelCredentialsRepository.save(credentials));
    }

    public PhysicalExaminationDTO saveExamination(PhysicalExaminationDTO examinationDTO) {
        PhysicalExamination examination = convertToExaminationEntity(examinationDTO);
        return convertToExaminationDTO(physicalExaminationRepository.save(examination));
    }

    public void deletePersonnel(String id) {
        personalDataRepository.deleteById(id);
    }

    public StaffWithRouteResponse getStaffWithRoutes(String tabelNumber) {
        Optional<PersonalData> personalDataOptional = personalDataRepository.findByTabelNumber(tabelNumber);
        if (personalDataOptional.isEmpty()) {
            return null;
        }

        PersonalDataDTO personalDataDTO = convertToDTO(personalDataOptional.get());
        PersonnelCredentialsDTO credentialsDTO = personnelCredentialsRepository.findByTabelNumber(tabelNumber)
                .map(this::convertToCredentialsDTO)
                .orElse(null);

        // Отримати маршрутні листи для цього працівника через REST-виклик до route-service
        String url = routeServiceUrl + "/api/route-sheets/driver/" + tabelNumber;
        RouteSheetDTO[] driverRouteSheets = restTemplate.getForObject(url, RouteSheetDTO[].class);

        // Також перевірити, чи працював як кондуктор
        url = routeServiceUrl + "/api/route-sheets/conductor/" + tabelNumber;
        RouteSheetDTO[] conductorRouteSheets = restTemplate.getForObject(url, RouteSheetDTO[].class);

        // Об'єднати всі маршрутні листи
        List<RouteSheetDTO> allRouteSheets = new java.util.ArrayList<>();
        if (driverRouteSheets != null) {
            allRouteSheets.addAll(List.of(driverRouteSheets));
        }
        if (conductorRouteSheets != null) {
            allRouteSheets.addAll(List.of(conductorRouteSheets));
        }

        return new StaffWithRouteResponse(personalDataDTO, credentialsDTO, allRouteSheets);
    }

    private PersonalDataDTO convertToDTO(PersonalData personalData) {
        PersonalDataDTO dto = new PersonalDataDTO();
        BeanUtils.copyProperties(personalData, dto);
        return dto;
    }

    private PersonalData convertToEntity(PersonalDataDTO dto) {
        PersonalData entity = new PersonalData();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }

    private PersonnelCredentialsDTO convertToCredentialsDTO(PersonnelCredentials credentials) {
        PersonnelCredentialsDTO dto = new PersonnelCredentialsDTO();
        BeanUtils.copyProperties(credentials, dto);
        return dto;
    }

    private PersonnelCredentials convertToCredentialsEntity(PersonnelCredentialsDTO dto) {
        PersonnelCredentials entity = new PersonnelCredentials();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }

    private PhysicalExaminationDTO convertToExaminationDTO(PhysicalExamination examination) {
        PhysicalExaminationDTO dto = new PhysicalExaminationDTO();
        BeanUtils.copyProperties(examination, dto);
        return dto;
    }

    private PhysicalExamination convertToExaminationEntity(PhysicalExaminationDTO dto) {
        PhysicalExamination entity = new PhysicalExamination();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }
}