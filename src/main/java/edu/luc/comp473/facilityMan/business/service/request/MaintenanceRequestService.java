package edu.luc.comp473.facilityMan.business.service.request;

import edu.luc.comp473.facilityMan.business.entities.maintenance.MaintenanceRequest;
import edu.luc.comp473.facilityMan.business.entities.maintenance.Problem;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Interface defining behariours regarding MaintenanceRequest.
 */
public interface MaintenanceRequestService {
    AtomicLong getAutoIncrementer();

    void setAutoIncrementer(AtomicLong autoIncrementer);

    MaintenanceRequest makeMaintenanceRequest(Problem problem, long maintenanceId);

    List<MaintenanceRequest> listMaintRequests();
}
