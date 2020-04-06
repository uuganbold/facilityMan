package edu.luc.comp473.facilityMan.business.facility.services;

import org.springframework.stereotype.Service;

import edu.luc.comp473.facilityMan.business.facility.dto.FacilityDetailRepresentation;
import edu.luc.comp473.facilityMan.business.facility.dto.FacilityDetailRequest;
import edu.luc.comp473.facilityMan.business.facility.dto.FacilityRepresentation;
import edu.luc.comp473.facilityMan.business.facility.entities.Facility;
import edu.luc.comp473.facilityMan.business.facility.entities.FacilityDetail;
import edu.luc.comp473.facilityMan.business.exceptions.EntityNotFoundException;
import edu.luc.comp473.facilityMan.persistence.facility.FacilityDetailRepository;
import edu.luc.comp473.facilityMan.persistence.facility.FacilityRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FacilityDetailServiceImpl implements FacilityDetailService {

    private final FacilityDetailRepository repository;
    private final FacilityRepository facilityRepository;

    @Override
    public FacilityDetailRepresentation getById(long id) {
        FacilityDetail b = repository.findById(id).orElse(null);
        if (b == null)
            return null;
        return FacilityDetailRepresentation.of(b).setFacility(FacilityRepresentation.of(b.getFacility()));
    }

    @Override
    public FacilityDetailRepresentation create(long id, FacilityDetailRequest dto) {
        Facility facility = facilityRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Facility not found with id:" + id));

        FacilityDetail detail = dto.toEntity();
        facility.addDetails(detail);
        facilityRepository.save(facility);
        return FacilityDetailRepresentation.of(detail);
    }

    @Override
    public void delete(long id) {
        // units should be deleted if hibernate mapping is correct.
        FacilityDetail detail = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Facility not found with id:" + id));
        repository.delete(detail);
    }

    @Override
    public FacilityDetailRepresentation save(long id, FacilityDetailRequest dto) {
        Facility facility = facilityRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Facility not found with id:" + id));

        FacilityDetail detail = facility.getDetail();
        detail.setAddress(dto.getAddress());
        detail.setDescription(dto.getDescription());
        repository.save(detail);
        return FacilityDetailRepresentation.of(detail);
    }

}