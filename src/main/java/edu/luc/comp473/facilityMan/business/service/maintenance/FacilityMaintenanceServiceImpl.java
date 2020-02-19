package edu.luc.comp473.facilityMan.business.service.maintenance;

import edu.luc.comp473.facilityMan.business.entities.maintenance.FacilityMaintenance;

import java.util.ArrayList;
import java.util.List;

/**
 * Simple @see FacilityMaintenanceService implementations depending on DAO.
 */
public class FacilityMaintenanceServiceImpl implements FacilityMaintenanceService {

    private List<FacilityMaintenance> maintenances = new ArrayList<>();

    @Override
    public void scheduleMaint(FacilityMaintenance maint) {
        maintenances.add(maint);
    }

    @Override
    public List<FacilityMaintenance> listMaintenance() {
        return maintenances;
    }
}
