package edu.luc.comp473.facilityMan.business.service;


import edu.luc.comp473.facilityMan.business.maintenance.FacilityMaintenance;

import java.util.List;

public interface FacilityMaintenanceService {
    void scheduleMaint(FacilityMaintenance maint);
    List<FacilityMaintenance> listMaintenance();
}
