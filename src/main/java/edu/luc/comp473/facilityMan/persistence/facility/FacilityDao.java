package edu.luc.comp473.facilityMan.persistence.facility;

import edu.luc.comp473.facilityMan.business.entities.facility.Facility;

import java.util.List;

/**
 * Data access object for Facility.
 */
public interface FacilityDao {
    void saveFacility(Facility facility);

    Facility findFacilityById(long id);

    List<Facility> findAllFacilities();

    boolean removeFacility(long id);
}
