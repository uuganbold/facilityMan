package edu.luc.comp473.facilityMan.business.service.request;

import edu.luc.comp473.facilityMan.business.entities.maintenance.FacilityMaintenance;
import edu.luc.comp473.facilityMan.business.entities.maintenance.FacilityMaintenanceRequest;
import edu.luc.comp473.facilityMan.business.entities.maintenance.Problem;
import edu.luc.comp473.facilityMan.persistence.inventory.maintenance.MaintenanceRequestDao;

import java.util.List;

/**
 * Simple @MaintenanceRequestService depending on DAO.
 */
public class MaintenanceRequestServiceImpl implements MaintenanceRequestService {
    private MaintenanceRequestDao maintenanceRequestDao;

    public MaintenanceRequestServiceImpl(MaintenanceRequestDao maintenanceRequestDao){
        this.maintenanceRequestDao = maintenanceRequestDao;
    }

    @Override
    public FacilityMaintenanceRequest makeFacilityMaintenanceReq(Problem problem, long id) {
        FacilityMaintenanceRequest facilityMaintenanceRequest = new FacilityMaintenanceRequest(id, problem.getDescription());
        maintenanceRequestDao.saveFacilityMaintenanceRequest(facilityMaintenanceRequest);
        return facilityMaintenanceRequest;
    }

    @Override
    public List<FacilityMaintenanceRequest> listMaintReq() {
        return maintenanceRequestDao.findAllFacilityMaintenanceRequests();
    }
}
