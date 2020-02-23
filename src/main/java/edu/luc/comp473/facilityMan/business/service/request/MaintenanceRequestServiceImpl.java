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
    public MaintenanceRequest makeMaintenanceRequest(Problem problem, long maintenanceId) {
        MaintenanceRequest maintenanceRequest = new MaintenanceRequest(autoIncrementer.incrementAndGet(), problem.getDescription());
        maintenanceDao.addMaintenanceRequest(maintenanceId, maintenanceRequest);
        return maintenanceRequest;
    }

    @Override
    public List<MaintenanceRequest> listMaintRequests() { return maintenanceDao.getAllMaintenanceRequests(); }
}