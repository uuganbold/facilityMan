package edu.luc.comp473.facilityMan.business.service;

import edu.luc.comp473.facilityMan.business.maintenance.FacilityMaintenanceRequest;
import edu.luc.comp473.facilityMan.business.maintenance.Problem;

import java.util.List;

public interface MaintenanceRequestService {
    FacilityMaintenanceRequest makeFacilityMaintenanceReq(Problem problem, long id);
    List<FacilityMaintenanceRequest> listMaintReq();
}
