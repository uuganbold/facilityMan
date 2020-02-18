package edu.luc.comp473.facilityMan.business.service.request;

import edu.luc.comp473.facilityMan.business.entities.maintenance.FacilityMaintenanceRequest;
import edu.luc.comp473.facilityMan.business.entities.maintenance.Problem;

import java.util.List;

public interface MaintenanceRequestService {
    FacilityMaintenanceRequest makeFacilityMaintenanceReq(Problem problem, long id);

    List<FacilityMaintenanceRequest> listMaintReq();
}
