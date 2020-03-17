package edu.luc.comp473.facilityMan.persistence.inspection;

import edu.luc.comp473.facilityMan.business.entities.inspection.FacilityInspection;

import java.util.List;
import java.util.Map;

public interface FacilityInspectionDao {
    void addFacilityInspection(FacilityInspection inspection);

    List<FacilityInspection> getAllFacilityInspections();

    void removeFacilityInspection(long id);
}
