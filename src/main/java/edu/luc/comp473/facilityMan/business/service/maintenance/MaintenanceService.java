package edu.luc.comp473.facilityMan.business.service.maintenance;

import edu.luc.comp473.facilityMan.business.entities.maintenance.Maintenance;

import java.math.BigDecimal;
import java.util.List;

/**
 * Interface declaring services regarding Maintenance.
 */
public interface MaintenanceService {
    void scheduleMaintenance(Maintenance maint);

    List<Maintenance> listMaintenance();

    BigDecimal calcMaintenanceCostForFacility(Long id);

    int calcDownTimeForFacility(Long id);
}
