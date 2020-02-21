package edu.luc.comp473.facilityMan.business.service.maintenance;

import edu.luc.comp473.facilityMan.business.entities.maintenance.Maintenance;
import edu.luc.comp473.facilityMan.business.entities.maintenance.MaintenanceOrder;
import edu.luc.comp473.facilityMan.persistence.inventory.maintenance.HashMapMaintenanceDao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Simple @see MaintenanceService implementations depending on DAO.
 */
public class MaintenanceServiceImpl implements MaintenanceService {

    private HashMapMaintenanceDao hashMapMaintenanceDao;

    public MaintenanceServiceImpl(HashMapMaintenanceDao hashMapMaintenanceDao){
        this.hashMapMaintenanceDao = hashMapMaintenanceDao;
    }

    @Override
    public void scheduleMaintenance(Maintenance maintenance) {
        hashMapMaintenanceDao.addMaintenance(maintenance);
    }

    @Override
    public List<Maintenance> listMaintenance() { return hashMapMaintenanceDao.getAllMaintenance(); }

    @Override
    public BigDecimal calcMaintenanceCostForFacility(Long id){
        BigDecimal costForFacility = new BigDecimal(0);
        List<Maintenance> allMaintenance = hashMapMaintenanceDao.getAllMaintenance();
        for(Maintenance maintenance : allMaintenance){
            if(maintenance.getFacility().getId() == id){
                List<MaintenanceOrder> maintenanceOrders = maintenance.getOrders();
                for(MaintenanceOrder maintenanceOrder : maintenanceOrders){
                    costForFacility.add(maintenanceOrder.getTotalCost());
                }
            }
        }
        return costForFacility;
    }

    @Override
    public int calcDownTimeForFacility(Long id){
        int downTimeForFacility = 0;
        List<Maintenance> allMaintenance = hashMapMaintenanceDao.getAllMaintenance();
        for(Maintenance maintenance : allMaintenance){
            if(maintenance.getFacility().getId() == id){
                downTimeForFacility += maintenance.getSchedule().getNumberOfDays();
            }
        }
        return downTimeForFacility;
    }
}
