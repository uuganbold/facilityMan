package edu.luc.comp473.facilityMan.persistence.inventory.maintenance;

import edu.luc.comp473.facilityMan.business.entities.maintenance.Maintenance;
import edu.luc.comp473.facilityMan.business.entities.maintenance.MaintenanceRequest;

import java.util.List;

public interface MaintenanceDao {
    void addMaintenance(Maintenance maintenance);

    void addMaintenanceRequest(long id, MaintenanceRequest maintenanceRequest);

    Maintenance getMaintenanceById(long id);

    MaintenanceRequest getMaintenanceRequestById(long id);

    List<Maintenance> getAllMaintenance();

    List<MaintenanceRequest> getAllMaintenanceRequests();

    void removeMaintenance(long id);

    void removeMaintenanceRequest(long id);
}
