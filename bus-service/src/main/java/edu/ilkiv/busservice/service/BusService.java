package edu.ilkiv.busservice.service;

import edu.ilkiv.busservice.model.Bus;
import edu.ilkiv.busservice.model.TechnicalExamination;
import edu.ilkiv.busservice.repository.BusRepository;
import edu.ilkiv.busservice.repository.TechnicalExaminationRepository;
import edu.ilkiv.comon.DTO.BusDTO;
import edu.ilkiv.comon.DTO.BusWithExaminationsResponse;
import edu.ilkiv.comon.DTO.TechnicalExaminationDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BusService {

    private final BusRepository busRepository;
    private final TechnicalExaminationRepository technicalExaminationRepository;

    @Autowired
    public BusService(BusRepository busRepository, TechnicalExaminationRepository technicalExaminationRepository) {
        this.busRepository = busRepository;
        this.technicalExaminationRepository = technicalExaminationRepository;
    }

    public List<BusDTO> getAllBuses() {
        return busRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public BusDTO getBusById(String id) {
        return busRepository.findById(id)
                .map(this::convertToDTO)
                .orElse(null);
    }

    public BusDTO getBusByCountryNumber(String countryNumber) {
        return busRepository.findByCountryNumber(countryNumber)
                .map(this::convertToDTO)
                .orElse(null);
    }

    public BusDTO saveBus(BusDTO busDTO) {
        Bus bus = convertToEntity(busDTO);
        return convertToDTO(busRepository.save(bus));
    }

    public void deleteBus(String id) {
        busRepository.deleteById(id);
    }

    public BusWithExaminationsResponse getBusWithExaminations(String busId) {
        Optional<Bus> busOptional = busRepository.findById(busId);
        if (busOptional.isEmpty()) {
            return null;
        }

        BusDTO busDTO = convertToDTO(busOptional.get());
        List<TechnicalExaminationDTO> examinations = technicalExaminationRepository.findByBusId(busId)
                .stream()
                .map(this::convertToExaminationDTO)
                .collect(Collectors.toList());

        return new BusWithExaminationsResponse(busDTO, examinations);
    }

    public TechnicalExaminationDTO saveExamination(TechnicalExaminationDTO examinationDTO) {
        TechnicalExamination examination = convertToExaminationEntity(examinationDTO);
        return convertToExaminationDTO(technicalExaminationRepository.save(examination));
    }

    private BusDTO convertToDTO(Bus bus) {
        BusDTO busDTO = new BusDTO();
        BeanUtils.copyProperties(bus, busDTO);
        return busDTO;
    }

    private Bus convertToEntity(BusDTO busDTO) {
        Bus bus = new Bus();
        BeanUtils.copyProperties(busDTO, bus);
        return bus;
    }

    private TechnicalExaminationDTO convertToExaminationDTO(TechnicalExamination examination) {
        TechnicalExaminationDTO examinationDTO = new TechnicalExaminationDTO();
        BeanUtils.copyProperties(examination, examinationDTO);
        return examinationDTO;
    }

    private TechnicalExamination convertToExaminationEntity(TechnicalExaminationDTO examinationDTO) {
        TechnicalExamination examination = new TechnicalExamination();
        BeanUtils.copyProperties(examinationDTO, examination);
        return examination;
    }
}