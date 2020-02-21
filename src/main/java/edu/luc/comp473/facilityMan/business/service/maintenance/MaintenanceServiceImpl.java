package edu.luc.comp473.facilityMan.business.service.maintenance;

import edu.luc.comp473.facilityMan.business.entities.maintenance.Maintenance;
import edu.luc.comp473.facilityMan.business.entities.maintenance.MaintenanceOrder;
import edu.luc.comp473.facilityMan.persistence.inventory.maintenance.HashMapMaintenanceDao;
import edu.luc.comp473.facilityMan.persistence.inventory.maintenance.MaintenanceDao;

import java.math.BigDecimal;
import java.util.List;

/**
 * Simple @see MaintenanceService implementations depending on DAO.
 */
public class MaintenanceServiceImpl implements MaintenanceService {

    private MaintenanceDao maintenanceDao;

    public MaintenanceServiceImpl(MaintenanceDao maintenanceDao){
        this.maintenanceDao = maintenanceDao;
    }

    @Override
    public void scheduleMaintenance(Maintenance maintenance) {
        maintenanceDao.addMaintenance(maintenance);
    }

    @Override
    public Maintenance getMaintenance(long id){ return maintenanceDao.getMaintenanceById(id); }

    @Override
    public List<Maintenance> listMaintenance() { return maintenanceDao.getAllMaintenance(); }

    @Override
    public BigDecimal calcMaintenanceCostForFacility(long id){
        BigDecimal costForFacility = new BigDecimal(0);
        List<Maintenance> allMaintenance = maintenanceDao.getAllMaintenance();
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
    public int calcDownTimeForFacility(long id){
        int downTimeForFacility = 0;
        List<Maintenance> allMaintenance = maintenanceDao.getAllMaintenance();
        for(Maintenance maintenance : allMaintenance){
            if(maintenance.getFacility().getId() == id){
                downTimeForFacility += maintenance.getSchedule().getNumberOfDays();
            }
        }
        return downTimeForFacility;
    }
}
