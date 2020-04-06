package edu.luc.comp473.facilityMan.business.facility.services;

import edu.luc.comp473.facilityMan.business.facility.dto.FacilityDetailRepresentation;
import edu.luc.comp473.facilityMan.business.facility.dto.FacilityDetailRequest;

public interface FacilityDetailService {

    FacilityDetailRepresentation getById(long id);

    FacilityDetailRepresentation create(long id, FacilityDetailRequest dto);

    FacilityDetailRepresentation save(long id, FacilityDetailRequest dto);

    void delete(long id);
}