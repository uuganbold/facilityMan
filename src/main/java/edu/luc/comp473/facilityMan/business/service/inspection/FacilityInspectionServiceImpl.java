package edu.luc.comp473.facilityMan.business.service.inspection;

import edu.luc.comp473.facilityMan.business.entities.inspection.FacilityInspection;
import edu.luc.comp473.facilityMan.persistence.inventory.inspection.FacilityInspectionDao;
import edu.luc.comp473.facilityMan.persistence.inventory.inspection.HashMapFacilityInspectionDao;

import java.util.HashMap;
import java.util.List;

/**
 * Simple @see FacilityInspectionService implementation depending on DAO.
 */
public class FacilityInspectionServiceImpl implements FacilityInspectionService {

    private FacilityInspectionDao hashMapFacilityInspectionDao;

    public FacilityInspectionServiceImpl(HashMapFacilityInspectionDao hashMapFacilityInspectionDao){
        this.hashMapFacilityInspectionDao = hashMapFacilityInspectionDao;
    }

    @Override
    public void addInspection(FacilityInspection inspection) { hashMapFacilityInspectionDao.addFacilityInspection(inspection); }

    @Override
    public List<FacilityInspection> listInspections() {
        return hashMapFacilityInspectionDao.getAllFacilityInspections();
    }
}
