package edu.luc.comp473.facilityMan.persistence.inventory;

import java.util.List;

import edu.luc.comp473.facilityMan.business.entities.facility.Facility;

/**
 * Data access object for Facility.
 */
public interface FacilityInventoryDao {

    void saveFacility(Facility facility);

    Facility findFacilityById(long id);

    List<Facility> findAllFacilities();

    void removeFacility(Facility facility);

}
