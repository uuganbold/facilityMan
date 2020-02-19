package edu.luc.comp473.facilityMan.business.service.inventory;

import edu.luc.comp473.facilityMan.business.entities.facility.Facility;
import edu.luc.comp473.facilityMan.business.entities.facility.FacilityDetail;

import java.util.List;

/**
 * Interface defining behaviours regarding Facility.
 */
public interface FacilityInventoryService {
    List<Facility> listFacilities();

    Facility getFacility(long id);

    void addNewFacility(Facility facility);

    boolean removeFacility(Long id);

    FacilityDetail getFacilityInformation(Long id);

    void addFacilityDetail(Long id, FacilityDetail detail);

    int requestAvailableCapacity(Long id);
}
