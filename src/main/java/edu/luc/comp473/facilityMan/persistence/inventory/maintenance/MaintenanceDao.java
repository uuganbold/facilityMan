package edu.luc.comp473.facilityMan.persistence.inventory.maintenance;

import edu.luc.comp473.facilityMan.business.entities.maintenance.Maintenance;
import edu.luc.comp473.facilityMan.business.entities.maintenance.MaintenanceRequest;

import java.util.List;

public interface MaintenanceDao {
    void addMaintenance(Maintenance maintenance);

    void addMaintenanceRequest(Long id, MaintenanceRequest maintenanceRequest);

    Maintenance getMaintenanceById(Long id);

    MaintenanceRequest getMaintenanceRequestById(long maintenanceId, long maintenanceRequestId);

    List<Maintenance> getAllMaintenance();

    List<MaintenanceRequest> getAllMaintenanceRequests();

    void removeMaintenanceRequest(MaintenanceRequest maintenanceRequest);
}
