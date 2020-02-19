package edu.luc.comp473.facilityMan.business.service.maintenance;

import edu.luc.comp473.facilityMan.business.entities.maintenance.FacilityMaintenance;

import java.util.List;

/**
 * Interface declaring services regarding FacilityMaintenance.
 */
public interface FacilityMaintenanceService {
    void scheduleMaint(FacilityMaintenance maint);

    List<FacilityMaintenance> listMaintenance();
}
