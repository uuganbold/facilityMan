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
    private final AtomicLong autoIncrementer = new AtomicLong(0);

    public HashMapMaintenanceDao(){ dataStore = new HashMap<>();}

    @Override
    public void addMaintenance(Maintenance maintenance){
        dataStore.put(maintenance.getId(), maintenance);
    }

    @Override
    public void addMaintenanceRequest(Long id, MaintenanceRequest maintenanceRequest){
        synchronized (dataStore){
            dataStore.get(id).addRequest(maintenanceRequest);
        }
    }

    @Override
    public Maintenance getMaintenanceById(Long id){
        if(!dataStore.containsKey(id)){
            throw new DataAccessException("Maintenance not found of id: " + id);
        }
        return dataStore.get(id);
    }

    @Override
    public MaintenanceRequest getMaintenanceRequestById(long maintenanceId, long maintenanceRequestId) {
        if(!dataStore.containsKey(maintenanceId)){
            throw new DataAccessException("Maintenance not found of id: " + maintenanceId);
        }
        Maintenance maintenance = dataStore.get(maintenanceId);
        for(MaintenanceRequest maintenanceRequest : maintenance.getRequests()){
            if(maintenanceRequest.getId() == maintenanceRequestId){
                return maintenanceRequest;
            }
        }
        throw new DataAccessException("Maintenance request not found of id: " + maintenanceRequestId);
    }

    @Override
    public List<Maintenance> getAllMaintenance(){
        return new ArrayList<>(dataStore.values());
    }

    @Override
    public List<MaintenanceRequest> getAllMaintenanceRequests() {
        ArrayList<MaintenanceRequest> maintenanceRequests = new ArrayList<>();
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
