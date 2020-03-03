package edu.luc.comp473.facilityMan.business.service.facility;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import edu.luc.comp473.facilityMan.business.service.facility.dto.FacilityDTO;
import edu.luc.comp473.facilityMan.persistence.facility.FacilityRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class FacilityServiceImpl implements FacilityService {

    private final FacilityRepository repository;

    @Override
    public List<FacilityDTO> listAll() {
        return repository.findAll().stream().map(FacilityDTO::of).collect(Collectors.toList());
    }

}