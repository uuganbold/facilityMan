package edu.luc.comp473.facilityMan.business.service.facility;

import edu.luc.comp473.facilityMan.business.service.facility.dto.FacilityDetailDTO;

public interface FacilityDetailService {

    FacilityDetailDTO getById(long id);

    FacilityDetailDTO create(FacilityDetailDTO dto);

    void save(FacilityDetailDTO dto);

    void delete(long id);
}