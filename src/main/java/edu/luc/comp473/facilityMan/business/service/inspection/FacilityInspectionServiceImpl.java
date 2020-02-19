package edu.luc.comp473.facilityMan.business.service.inspection;

import edu.luc.comp473.facilityMan.business.entities.inspection.FacilityInspection;

import java.util.ArrayList;
import java.util.List;

/**
 * Simple @see FacilityInspectionService implementation depending on DAO.
 */
public class FacilityInspectionServiceImpl implements FacilityInspectionService {
    private List<FacilityInspection> inspections = new ArrayList<>();

    @Override
    public List<FacilityInspection> listInspection(long id) {
        /*
         * Facility f = FacilityInventoryServiceImpl.getInstance().getFacility(id);
         *
         * List<FacilityInspection> currInspections = new ArrayList<>();
         *
         * if (f != null) { for (FacilityInspection i : inspections) { if
         * (i.getFacility().getId() == id) { currInspections.add(i); } } } return
         * currInspections;
         */
        return null;
    }

    @Override
    public void addInspection(FacilityInspection inspection) {
        inspections.add(inspection);
    }
}
