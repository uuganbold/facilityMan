package edu.luc.comp473.facilityMan.business.facility.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import edu.luc.comp473.facilityMan.business.facility.dto.FacilityRepresentation;
import edu.luc.comp473.facilityMan.persistence.facility.FacilityRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class FacilityServiceImpl implements FacilityService {

    private final FacilityRepository repository;

    @Override
    public List<FacilityRepresentation> listAll() {
        return repository.findAll().stream().map(FacilityRepresentation::of).collect(Collectors.toList());
    }

}