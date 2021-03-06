package edu.luc.comp473.facilityMan.persistence.facility;

import edu.luc.comp473.facilityMan.business.entities.facility.Building;
import edu.luc.comp473.facilityMan.business.entities.facility.Facility;
import edu.luc.comp473.facilityMan.business.entities.facility.Unit;
import edu.luc.comp473.facilityMan.business.exceptions.DataAccessException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/**
 * HashMap implementation of @see . Singleton pattern to avoid multiple data stores.
 */
public class HashMapFacilityDao implements FacilityDao {
    private final Map<Long, Facility> dataStore;
    private final AtomicLong autoIncrementer = new AtomicLong(0);

    public HashMapFacilityDao(Map<Long, Facility> dataStore){ this.dataStore = dataStore; }

    @Override
    public List<Facility> findAllFacilities() {
        return new ArrayList<>(dataStore.values());
    }

    @Override
    public Facility findFacilityById(long id) {
        return dataStore.get(id);
    }

    @Override
    public boolean removeFacility(long id) {
        if (!dataStore.containsKey(id)) {
            return false;
        }
        Facility f = dataStore.get(id);

        if (f instanceof Building) {
            Building building = (Building) f;
            List<Unit> units = building.getUnits();
            while (!units.isEmpty()) {
                Unit u = units.get(units.size() - 1);
                synchronized (dataStore) {
                    dataStore.remove(u.getId());
                }
                building.removeUnit(u);
//                u.setBuilding(null);
            }
        } else if (f instanceof Unit) {
            Unit unit = (Unit) f;

            for (Facility fac: dataStore.values()){
                if (fac instanceof Building){
                    if (((Building) fac).getUnits().contains(unit)){
                        ((Building)fac).removeUnit(unit);
                    }
                }
            }
//            unit.getBuilding().removeUnit(unit);
        }
        synchronized (dataStore) {
            dataStore.remove(id);
        }
        return true;
    }

    @Override
    public void saveFacility(Facility facility) {
        if (facility.getId() == 0) {
            facility.setId(autoIncrementer.incrementAndGet());
        } else {
            if (!dataStore.containsKey(facility.getId())) {
                throw new DataAccessException("Facility has not been found with the id:" + facility.getId());
            } else if (!dataStore.get(facility.getId()).getClass().equals(facility.getClass())) {
                throw new DataAccessException("Type of the facility does not match");
            }
        }
        synchronized (dataStore) {
            dataStore.put(facility.getId(), facility);
        }
    }

    public Map<Long, Facility> getDataStore(){ return dataStore; }
}
