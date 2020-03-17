package edu.luc.comp473.facilityMan.business.service.information;

import edu.luc.comp473.facilityMan.business.entities.facility.Facility;
import edu.luc.comp473.facilityMan.business.entities.facility.FacilityDetail;
import edu.luc.comp473.facilityMan.persistence.facility.FacilityDao;

/**
 * Interface defining behaviours regarding FacilityInspection.
 */
public interface FacilityInformationService {
    FacilityDetail getFacilityInformation(Long id);

    void addFacilityDetail(Long id, FacilityDetail detail);

    int requestAvailableCapacity(Long id);
}
