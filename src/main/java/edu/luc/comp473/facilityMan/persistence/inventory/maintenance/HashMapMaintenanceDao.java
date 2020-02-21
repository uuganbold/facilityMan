package edu.luc.comp473.facilityMan.persistence.inventory.maintenance;

import edu.luc.comp473.facilityMan.business.entities.maintenance.Maintenance;
import edu.luc.comp473.facilityMan.business.entities.maintenance.MaintenanceRequest;
import edu.luc.comp473.facilityMan.business.exceptions.DataAccessException;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 * HashMap implementation of MaintenanceDao
 */
public class HashMapMaintenanceDao implements MaintenanceDao {
    private final Map<Long, Maintenance> dataStore;

    public HashMapMaintenanceDao(HashMap<Long, Maintenance> dataStore){ this.dataStore = dataStore;}

    @Override
    public void addMaintenance(Maintenance maintenance){
        dataStore.put(maintenance.getId(), maintenance);
    }

    @Override
    public void addMaintenanceRequest(long id, MaintenanceRequest maintenanceRequest){
        synchronized (dataStore){
            dataStore.get(id).addRequest(maintenanceRequest);
        }
    }

    @Override
    public Maintenance getMaintenanceById(long id){
        if(!dataStore.containsKey(id)){
            throw new DataAccessException("Maintenance not found of id: " + id);
        }
        return dataStore.get(id);
    }

    @Override
    public MaintenanceRequest getMaintenanceRequestById(long id) {
        List<MaintenanceRequest> allMaintenanceRequests = this.getAllMaintenanceRequests();
        for(MaintenanceRequest maintenanceRequest : allMaintenanceRequests){
            if(maintenanceRequest.getId() == id){
                return maintenanceRequest;
            }
        }
        throw new DataAccessException("Maintenance request not found of id: " + id);
    }

    @Override
    public List<Maintenance> getAllMaintenance(){
        return new ArrayList<>(dataStore.values());
    }

    @Override
    public List<MaintenanceRequest> getAllMaintenanceRequests() {
        List<MaintenanceRequest> maintenanceRequests = new ArrayList<>();
        for(Maintenance maintenance : dataStore.values()){
            maintenanceRequests.addAll(maintenance.getRequests());
        }
        return maintenanceRequests;
    }

    @Override
    public void removeMaintenanceRequest(MaintenanceRequest maintenanceRequest) {
        if (!dataStore.containsKey(maintenanceRequest.getId())) {
            return;
        }
        synchronized (dataStore) {
            dataStore.remove(maintenanceRequest.getId());
        }
    }
}
