package edu.luc.comp473.facilityMan.business.service.inventory;

import edu.luc.comp473.facilityMan.business.entities.facility.Facility;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Interface defining behaviours regarding Facility.
 */
public interface FacilityInventoryService {
    List<Facility> listFacilities();

    void addNewFacility(Facility facility);

    boolean removeFacility(long id);

    Facility getFacility(long id);
}
