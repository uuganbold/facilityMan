package edu.luc.comp473.facilityMan.business.service.request;

import edu.luc.comp473.facilityMan.business.entities.maintenance.MaintenanceRequest;
import edu.luc.comp473.facilityMan.business.entities.maintenance.Problem;

import java.util.List;

/**
 * Interface defining behariours regarding MaintenanceRequest.
 */
public interface MaintenanceRequestService {
    MaintenanceRequest makeMaintenanceReq(Problem problem, long id);

    List<MaintenanceRequest> listMaintReq();
}
