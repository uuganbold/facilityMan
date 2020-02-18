package edu.luc.comp473.facilityMan.business.service.inventory;

import edu.luc.comp473.facilityMan.business.entities.facility.Facility;

import java.util.List;

public interface FacilityInventoryService {
    List<Facility> listFacilities();

    void addNewFacility(Facility facility);

    boolean removeFacility(Long id);
}
