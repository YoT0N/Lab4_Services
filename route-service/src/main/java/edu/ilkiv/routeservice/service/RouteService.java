package edu.ilkiv.routeservice.service;

import edu.ilkiv.comon.DTO.BusDTO;
import edu.ilkiv.comon.DTO.PersonalDataDTO;
import edu.ilkiv.comon.DTO.RouteDTO;
import edu.ilkiv.comon.DTO.RouteSheetDTO;
import edu.ilkiv.routeservice.model.Route;
import edu.ilkiv.routeservice.model.RouteSheet;
import edu.ilkiv.routeservice.repository.RouteRepository;
import edu.ilkiv.routeservice.repository.RouteSheetRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RouteService {

    private final RouteRepository routeRepository;
    private final RouteSheetRepository routeSheetRepository;
    private final RestTemplate restTemplate;

    @Value("${bus.service.url}")
    private String busServiceUrl;

    @Value("${personnel.service.url}")
    private String personnelServiceUrl;

    @Autowired
    public RouteService(RouteRepository routeRepository,
                        RouteSheetRepository routeSheetRepository,
                        RestTemplate restTemplate) {
        this.routeRepository = routeRepository;
        this.routeSheetRepository = routeSheetRepository;
        this.restTemplate = restTemplate;
    }

    public List<RouteDTO> getAllRoutes() {
        return routeRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public RouteDTO getRouteById(String id) {
        return routeRepository.findById(id)
                .map(this::convertToDTO)
                .orElse(null);
    }

    public RouteDTO getRouteByNumber(String routeNumber) {
        return routeRepository.findByRouteNumber(routeNumber)
                .map(this::convertToDTO)
                .orElse(null);
    }

    public RouteDTO saveRoute(RouteDTO routeDTO) {
        Route route = convertToEntity(routeDTO);
        return convertToDTO(routeRepository.save(route));
    }

    public void deleteRoute(String id) {
        routeRepository.deleteById(id);
    }

    public List<RouteSheetDTO> getRouteSheetsByRouteId(String routeId) {
        return routeSheetRepository.findByRouteId(routeId).stream()
                .map(this::convertToRouteSheetDTO)
                .collect(Collectors.toList());
    }

    public List<RouteSheetDTO> getRouteSheetsByDriverTabelNumber(String tabelNumber) {
        return routeSheetRepository.findByDriverTabelNumber(tabelNumber).stream()
                .map(this::convertToRouteSheetDTO)
                .collect(Collectors.toList());
    }

    public List<RouteSheetDTO> getRouteSheetsByConductorTabelNumber(String tabelNumber) {
        return routeSheetRepository.findByConductorTabelNumber(tabelNumber).stream()
                .map(this::convertToRouteSheetDTO)
                .collect(Collectors.toList());
    }

    public List<RouteSheetDTO> getRouteSheetsByDate(LocalDate date) {
        return routeSheetRepository.findByDate(date).stream()
                .map(this::convertToRouteSheetDTO)
                .collect(Collectors.toList());
    }

    public RouteSheetDTO saveRouteSheet(RouteSheetDTO routeSheetDTO) {
        // Перевірка, чи існує маршрут
        RouteDTO routeDTO = getRouteById(routeSheetDTO.getRouteId());
        if (routeDTO == null) {
            throw new RuntimeException("Route not found");
        }

        // Перевірка, чи існує автобус через REST-виклик до bus-service
        String busUrl = busServiceUrl + "/api/buses/" + routeSheetDTO.getBusId();
        BusDTO busDTO = restTemplate.getForObject(busUrl, BusDTO.class);
        if (busDTO == null) {
            throw new RuntimeException("Bus not found");
        }

        // Перевірка, чи існує водій через REST-виклик до personnel-service
        String driverUrl = personnelServiceUrl + "/api/personnel/" + routeSheetDTO.getDriverId();
        PersonalDataDTO driverDTO = restTemplate.getForObject(driverUrl, PersonalDataDTO.class);
        if (driverDTO == null) {
            throw new RuntimeException("Driver not found");
        }

        // Перевірка, чи існує кондуктор через REST-виклик до personnel-service
        String conductorUrl = personnelServiceUrl + "/api/personnel/" + routeSheetDTO.getConductorId();
        PersonalDataDTO conductorDTO = restTemplate.getForObject(conductorUrl, PersonalDataDTO.class);
        if (conductorDTO == null) {
            throw new RuntimeException("Conductor not found");
        }

        // Якщо всі перевірки пройшли успішно, зберігаємо маршрутний лист
        RouteSheet routeSheet = convertToRouteSheetEntity(routeSheetDTO);
        return convertToRouteSheetDTO(routeSheetRepository.save(routeSheet));
    }

    private RouteDTO convertToDTO(Route route) {
        RouteDTO routeDTO = new RouteDTO();
        BeanUtils.copyProperties(route, routeDTO);
        return routeDTO;
    }

    private Route convertToEntity(RouteDTO routeDTO) {
        Route route = new Route();
        BeanUtils.copyProperties(routeDTO, route);
        return route;
    }

    private RouteSheetDTO convertToRouteSheetDTO(RouteSheet routeSheet) {
        RouteSheetDTO routeSheetDTO = new RouteSheetDTO();
        BeanUtils.copyProperties(routeSheet, routeSheetDTO);
        return routeSheetDTO;
    }

    private RouteSheet convertToRouteSheetEntity(RouteSheetDTO routeSheetDTO) {
        RouteSheet routeSheet = new RouteSheet();
        BeanUtils.copyProperties(routeSheetDTO, routeSheet);
        return routeSheet;
    }
}