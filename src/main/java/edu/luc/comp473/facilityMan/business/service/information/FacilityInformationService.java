package edu.luc.comp473.facilityMan.business.service.information;

import edu.luc.comp473.facilityMan.business.entities.facility.FacilityDetail;

public interface FacilityInformationService {
    FacilityDetail getFacilityInformation(Long id);

    void addFacilityDetail(Long id, FacilityDetail detail);

    int requestAvailableCapacity(Long id);
}
