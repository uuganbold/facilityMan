package edu.luc.comp473.facilityMan.business.service;

import edu.luc.comp473.facilityMan.business.inspection.FacilityInspection;

import java.util.List;

public interface FacilityInspectionService {
    List<FacilityInspection> listInspection(long id);
    void addInspection(FacilityInspection inspection);
}
