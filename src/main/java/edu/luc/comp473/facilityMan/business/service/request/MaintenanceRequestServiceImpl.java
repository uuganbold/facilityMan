package edu.luc.comp473.facilityMan.business.service.request;

import edu.luc.comp473.facilityMan.business.entities.maintenance.MaintenanceRequest;
import edu.luc.comp473.facilityMan.business.entities.maintenance.Problem;
import edu.luc.comp473.facilityMan.persistence.maintenance.MaintenanceDao;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Simple @MaintenanceRequestService depending on DAO.
 */
public class MaintenanceRequestServiceImpl implements MaintenanceRequestService {
    private final MaintenanceDao maintenanceDao;
    private AtomicLong autoIncrementer = new AtomicLong();

    public MaintenanceRequestServiceImpl(MaintenanceDao maintenanceDao){
        this.maintenanceDao = maintenanceDao;
    }

    @Override
    public AtomicLong getAutoIncrementer(){ return this.autoIncrementer; }

    @Override
    public void setAutoIncrementer(AtomicLong autoIncrementer){ this.autoIncrementer = autoIncrementer; }

    @Override
    public MaintenanceRequest makeMaintenanceRequest(Problem problem, long maintenanceId) {
        MaintenanceRequest maintenanceRequest = new MaintenanceRequest(autoIncrementer.incrementAndGet(), problem.getDescription());
        maintenanceDao.addMaintenanceRequest(maintenanceId, maintenanceRequest);
        return maintenanceRequest;
    }

    @Override
    public List<MaintenanceRequest> listMaintRequests() { return maintenanceDao.getAllMaintenanceRequests(); }
}