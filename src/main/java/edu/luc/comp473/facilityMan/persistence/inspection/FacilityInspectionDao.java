package edu.luc.comp473.facilityMan.persistence.inspection;

import edu.luc.comp473.facilityMan.business.entities.inspection.FacilityInspection;

import java.util.List;

public interface FacilityInspectionDao {
    public void addFacilityInspection(FacilityInspection inspection);

    public List<FacilityInspection> getAllFacilityInspections();

    public void removeFacilityInspection(long id);
}
