package edu.luc.comp473.facilityMan.business.service.inspection;

import edu.luc.comp473.facilityMan.business.entities.inspection.FacilityInspection;

import java.util.List;

/**
 * Interface defining behaviours regarding FacilityInspection.
 */
public interface FacilityInspectionService {
    void addInspection(FacilityInspection inspection);

    List<FacilityInspection> listInspections();
}
