package edu.luc.comp473.facilityMan.business.service.maintenance;

import edu.luc.comp473.facilityMan.business.entities.maintenance.Maintenance;
import edu.luc.comp473.facilityMan.business.entities.maintenance.Problem;

import java.math.BigDecimal;
import java.util.List;

/**
 * Interface declaring services regarding Maintenance.
 */
public interface MaintenanceService {
    void scheduleMaintenance(Maintenance maint);

    List<Maintenance> listMaintenance();

    BigDecimal calcMaintenanceCostForFacility(long id);

    int calcDownTimeForFacility(long id);

    int calcProblemRateForFacility(long id);

    List<Problem> listFacilityProblems(long id);
}
