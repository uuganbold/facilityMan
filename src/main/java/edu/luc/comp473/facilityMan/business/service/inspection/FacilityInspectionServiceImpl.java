package edu.luc.comp473.facilityMan.business.service.inspection;

import edu.luc.comp473.facilityMan.business.entities.inspection.FacilityInspection;
import edu.luc.comp473.facilityMan.persistence.inspection.FacilityInspectionDao;

import java.util.List;

/**
 * Simple @see FacilityInspectionService implementation depending on DAO.
 */
public class FacilityInspectionServiceImpl implements FacilityInspectionService {

    private final FacilityInspectionDao facilityInspectionDao;

    public FacilityInspectionServiceImpl(FacilityInspectionDao facilityInspectionDao){
        this.facilityInspectionDao = facilityInspectionDao;
    }

    @Override
    public void addInspection(FacilityInspection inspection) { facilityInspectionDao.addFacilityInspection(inspection); }

    @Override
    public List<FacilityInspection> listInspections() {
        return facilityInspectionDao.getAllFacilityInspections();
    }
}
