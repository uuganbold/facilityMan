package edu.luc.comp473.facilityMan.persistence.inventory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.ArrayList;

import edu.luc.comp473.facilityMan.business.entities.facility.Building;
import edu.luc.comp473.facilityMan.business.entities.facility.Facility;
import edu.luc.comp473.facilityMan.business.entities.facility.Unit;
import edu.luc.comp473.facilityMan.business.exceptions.DataAccessException;

/**
 * HashMap implementation of @see FacilityInventoryDao.
 */
public class HashMapFacilityInventoryDao implements FacilityInventoryDao {
    private final Map<Long, Facility> dataStore;
    private final AtomicLong autoIncrementer = new AtomicLong(0);

    public HashMapFacilityInventoryDao() {
        dataStore = new HashMap<>();
    }

    public HashMapFacilityInventoryDao(HashMap<Long, Facility> dataStore) {
        this.dataStore = dataStore;
    }

    @Override
    public List<Facility> findAllFacilities() {
        return new ArrayList<Facility>(dataStore.values());
    }

    @Override
    public Facility findFacilityById(long id) {
        return dataStore.get(id);
    }

    @Override
    public void removeFacility(Facility facility) {
        if (!dataStore.containsKey(facility.getId())) {
            return;
        }
        if (facility instanceof Building) {
            Building building = (Building) facility;
            ArrayList<Unit> units = building.getUnits();
            while (!units.isEmpty()) {
                Unit u = units.get(units.size() - 1);
                synchronized (dataStore) {
                    dataStore.remove(u.getId());
                }
                building.removeUnit(u);
            }
        } else if (facility instanceof Unit) {
            Unit unit = (Unit) facility;
            unit.getBuilding().removeUnit(unit);
        }
        synchronized (dataStore) {
            dataStore.remove(facility.getId());
        }
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

}
