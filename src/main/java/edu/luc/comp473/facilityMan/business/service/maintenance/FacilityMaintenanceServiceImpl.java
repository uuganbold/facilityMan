package edu.luc.comp473.facilityMan.business.service.maintenance;

import edu.luc.comp473.facilityMan.business.entities.maintenance.FacilityMaintenance;

import java.util.ArrayList;
import java.util.List;

public class FacilityMaintenanceServiceImpl implements FacilityMaintenanceService {

    List<FacilityMaintenance> maintenances = new ArrayList<>();

    @Override
    public void scheduleMaint(FacilityMaintenance maint) {
        maintenances.add(maint);
    }

    @Override
    public List<FacilityMaintenance> listMaintenance() {
        return maintenances;
    }
}
