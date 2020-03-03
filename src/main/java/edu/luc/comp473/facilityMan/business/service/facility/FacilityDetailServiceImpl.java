package edu.luc.comp473.facilityMan.business.service.facility;

import org.springframework.stereotype.Service;

import edu.luc.comp473.facilityMan.business.entities.facility.Facility;
import edu.luc.comp473.facilityMan.business.entities.facility.FacilityDetail;
import edu.luc.comp473.facilityMan.business.exceptions.EntityNotFoundException;
import edu.luc.comp473.facilityMan.business.service.facility.dto.FacilityDTO;
import edu.luc.comp473.facilityMan.business.service.facility.dto.FacilityDetailDTO;
import edu.luc.comp473.facilityMan.persistence.facility.FacilityDetailRepository;
import edu.luc.comp473.facilityMan.persistence.facility.FacilityRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FacilityDetailServiceImpl implements FacilityDetailService {

    private final FacilityDetailRepository repository;
    private final FacilityRepository facilityRepository;

    @Override
    public FacilityDetailDTO getById(long id) {
        FacilityDetail b = repository.findById(id).orElse(null);
        if (b == null)
            return null;
        return FacilityDetailDTO.of(b).setFacility(FacilityDTO.of(b.getFacility()));
    }

    @Override
    public FacilityDetailDTO create(FacilityDetailDTO dto) {
        Facility facility = facilityRepository.findById(dto.getId())
                .orElseThrow(() -> new EntityNotFoundException("Facility not found with id:" + dto.getId()));

        FacilityDetail detail = dto.toEntity();
        facility.addDetails(detail);
        facilityRepository.save(facility);
        return dto.setFacility(FacilityDTO.of(facility));
    }

    @Override
    public void delete(long id) {
        // units should be deleted if hibernate mapping is correct.
        FacilityDetail detail = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Facility not found with id:" + id));
        repository.delete(detail);
    }

    @Override
    public void save(FacilityDetailDTO dto) {
        Facility facility = facilityRepository.findById(dto.getId())
                .orElseThrow(() -> new EntityNotFoundException("Facility not found with id:" + dto.getId()));

        FacilityDetail detail = facility.getDetail();
        detail.setAddress(dto.getAddress());
        detail.setDescription(dto.getDescription());
        repository.save(detail);
        dto.setFacility(FacilityDTO.of(facility));
    }

}