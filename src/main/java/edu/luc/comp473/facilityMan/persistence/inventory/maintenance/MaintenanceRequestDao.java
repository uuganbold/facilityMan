package edu.luc.comp473.facilityMan.persistence.inventory.maintenance;

import edu.luc.comp473.facilityMan.business.entities.facility.Facility;
import edu.luc.comp473.facilityMan.business.entities.maintenance.FacilityMaintenanceRequest;

import java.util.List;

public interface MaintenanceRequestDao {
    void saveFacilityMaintenanceRequest(FacilityMaintenanceRequest facilityMaintenanceRequest);

    FacilityMaintenanceRequest findFacilityMaintenanceRequestById(long id);

    List<FacilityMaintenanceRequest> findAllFacilityMaintenanceRequests();

    void removeFacilityMaintenanceRequest(FacilityMaintenanceRequest facilityMaintenanceRequest);
}