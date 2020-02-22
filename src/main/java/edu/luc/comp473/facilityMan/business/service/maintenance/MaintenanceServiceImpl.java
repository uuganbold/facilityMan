package edu.luc.comp473.facilityMan.business.service.maintenance;

import edu.luc.comp473.facilityMan.business.entities.maintenance.Maintenance;
import edu.luc.comp473.facilityMan.business.entities.maintenance.Problem;
import edu.luc.comp473.facilityMan.business.entities.maintenance.MaintenanceOrder;
import edu.luc.comp473.facilityMan.business.exceptions.DataAccessException;
import edu.luc.comp473.facilityMan.persistence.inventory.maintenance.MaintenanceDao;
import java.math.BigDecimal;
import java.math.MathContext;
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
    public List<Maintenance> listMaintenance() { return maintenanceDao.getAllMaintenance(); }

    @Override
    public BigDecimal calcMaintenanceCostForFacility(long id){
        BigDecimal costForFacility = new BigDecimal(0);
        List<Maintenance> allMaintenance = maintenanceDao.getAllMaintenance();
        for(Maintenance maintenance : allMaintenance){
            if(maintenance.getFacility().getId() == id){
                List<MaintenanceOrder> maintenanceOrders = maintenance.getOrders();
                for(MaintenanceOrder maintenanceOrder : maintenanceOrders){
                    costForFacility = costForFacility.add(maintenanceOrder.getTotalCost(), new MathContext(2));
                }
                return costForFacility;
            }
        }
        throw new DataAccessException("Facility not found with id: " + id);
    }

    @Override
    public int calcDownTimeForFacility(long id){
        int downTimeForFacility = 0;
        List<Maintenance> allMaintenance = maintenanceDao.getAllMaintenance();
        for(Maintenance maintenance : allMaintenance){
            if(maintenance.getFacility().getId() == id){
                downTimeForFacility += maintenance.getSchedule().getNumberOfDays();
                return downTimeForFacility;
            }
        }
        throw new DataAccessException("Facility not found with id: " + id);
    }

    @Override
    //TODO figure out what this method is supposed to do
    public int calcProblemRateForFacility(long id){
        for(Maintenance maintenance : maintenanceDao.getAllMaintenance()){
            if(maintenance.getFacility().getId() == id){
                return maintenance.getProblems().size();
            }
        }
        throw new DataAccessException("Facility not found with id: " + id);
    }

    @Override
    public List<Problem> listFacilityProblems(long id){
        for(Maintenance maintenance : maintenanceDao.getAllMaintenance()){
            if(maintenance.getFacility().getId() == id){
                return maintenance.getProblems();
            }
        }
        throw new DataAccessException("Facility not found with id: " + id);
    }
}