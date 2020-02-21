package edu.luc.comp473.facilityMan.business.service.request;

import edu.luc.comp473.facilityMan.business.entities.maintenance.MaintenanceRequest;
import edu.luc.comp473.facilityMan.business.entities.maintenance.Problem;
import edu.luc.comp473.facilityMan.persistence.inventory.maintenance.MaintenanceDao;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Simple @MaintenanceRequestService depending on DAO.
 */
public class MaintenanceRequestServiceImpl implements MaintenanceRequestService {
    private MaintenanceDao maintenanceDao;
    private final AtomicLong autoIncrementer = new AtomicLong(0);

    public MaintenanceRequestServiceImpl(MaintenanceDao maintenanceDao){
        this.maintenanceDao = maintenanceDao;
    }

    @Override
    public MaintenanceRequest makeMaintenanceReq(Problem problem, long id) {
        MaintenanceRequest maintenanceRequest = new MaintenanceRequest(autoIncrementer.incrementAndGet(), problem.getDescription());
        maintenanceDao.addMaintenanceRequest(id, maintenanceRequest);
        return maintenanceRequest;
    }

    @Override
    public List<MaintenanceRequest> listMaintReq() {
        return maintenanceDao.getAllMaintenanceRequests();
    }
}
