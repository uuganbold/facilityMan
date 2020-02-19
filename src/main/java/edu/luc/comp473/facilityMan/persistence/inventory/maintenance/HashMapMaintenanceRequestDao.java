package edu.luc.comp473.facilityMan.persistence.inventory.maintenance;

import edu.luc.comp473.facilityMan.business.entities.maintenance.FacilityMaintenanceRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/**
 * HashMap implementation of MaintenanceRequestDao
 */
public class HashMapMaintenanceRequestDao implements MaintenanceRequestDao{
    private final Map<Long, FacilityMaintenanceRequest> dataStore;
    private final AtomicLong autoIncrementer = new AtomicLong(0);

    public HashMapMaintenanceRequestDao(){ dataStore = new HashMap<>();}

    @Override
    public void saveFacilityMaintenanceRequest(FacilityMaintenanceRequest facilityMaintenanceRequest){
        if (facilityMaintenanceRequest.getId() == 0){
            facilityMaintenanceRequest.setId(autoIncrementer.incrementAndGet());
        }
        synchronized (dataStore){
            dataStore.put(facilityMaintenanceRequest.getId(), facilityMaintenanceRequest);
        }
    }

    @Override
    public FacilityMaintenanceRequest findFacilityMaintenanceRequestById(long id) { return dataStore.get(id); }

    @Override
    public List<FacilityMaintenanceRequest> findAllFacilityMaintenanceRequests() {
        return new ArrayList<FacilityMaintenanceRequest>(dataStore.values());
    }

    @Override
    public void removeFacilityMaintenanceRequest(FacilityMaintenanceRequest facilityMaintenanceRequest) {
        if (!dataStore.containsKey(facilityMaintenanceRequest.getId())) {
            return;
        }
        synchronized (dataStore) {
            dataStore.remove(facilityMaintenanceRequest.getId());
        }
    }
}
