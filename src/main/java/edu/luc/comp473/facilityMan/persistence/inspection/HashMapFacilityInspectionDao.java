package edu.luc.comp473.facilityMan.persistence.inspection;

import edu.luc.comp473.facilityMan.business.entities.inspection.FacilityInspection;
import edu.luc.comp473.facilityMan.business.exceptions.DataAccessException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HashMapFacilityInspectionDao implements FacilityInspectionDao{
    private final Map<Long, FacilityInspection> dataStore;

    public HashMapFacilityInspectionDao(Map<Long, FacilityInspection> dataStore){ this.dataStore = dataStore; }

    @Override
    public void addFacilityInspection(FacilityInspection inspection){
        synchronized(dataStore){
            dataStore.put(inspection.getId(), inspection);
        }
    }

    @Override
    public List<FacilityInspection> getAllFacilityInspections(){ return new ArrayList<>(dataStore.values()); }

    @Override
    public void removeFacilityInspection(long id){
        if(!dataStore.containsKey(id)){
            throw new DataAccessException("Inspection not found with id: " + id);
        }
        dataStore.remove(id);
    }
}
