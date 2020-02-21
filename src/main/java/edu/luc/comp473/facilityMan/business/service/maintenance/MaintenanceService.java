package edu.luc.comp473.facilityMan.business.service.maintenance;

import edu.luc.comp473.facilityMan.business.entities.maintenance.Maintenance;

import java.math.BigDecimal;
import java.util.List;

/**
 * Interface declaring services regarding Maintenance.
 */
public interface MaintenanceService {
    void scheduleMaintenance(Maintenance maint);

    Maintenance getMaintenance(long id);

    List<Maintenance> listMaintenance();

    BigDecimal calcMaintenanceCostForFacility(long id);

    int calcDownTimeForFacility(long id);
}
