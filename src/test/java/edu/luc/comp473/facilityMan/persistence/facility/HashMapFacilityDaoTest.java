package edu.luc.comp473.facilityMan.persistence.facility;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

import edu.luc.comp473.facilityMan.business.entities.facility.Building;
import edu.luc.comp473.facilityMan.business.entities.facility.Facility;
import edu.luc.comp473.facilityMan.business.entities.facility.Unit;
import edu.luc.comp473.facilityMan.persistence.facility.FacilityDao;
import edu.luc.comp473.facilityMan.persistence.facility.HashMapFacilityDao;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import edu.luc.comp473.facilityMan.business.exceptions.DataAccessException;

/**
 * Tests behaviours regarding @see HashMapFacilityDao.
 */
@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
public class HashMapFacilityDaoTest {

    private HashMap<Long, Facility> dataStore;

    private HashMapFacilityDao dao;

    @BeforeAll
    public void init() {
        dao = new HashMapFacilityDao();
        dataStore = dao.getDataStore();
    }

    @Test
    @Order(2)
    public void givenEmptyStore_whenSaveFacility_thenSaveIt() {
        // given
        assertTrue(dataStore.isEmpty());
        Facility facility = new Building();

        // when
        dao.saveFacility(facility);

        // then
        assertEquals(1, dataStore.size());
        assertTrue(dataStore.containsValue(facility));
    }

    @Test
    @Order(3)
    public void givenNewFacility_whenSaveFacility_thenIdAutoIncrement() {
        // given
        int sizeAtStart = dataStore.size();
        Facility facility1 = new Building();
        Facility facility2 = new Unit((Building) facility1);
        Facility facility3 = new Building();

        // when
        dao.saveFacility(facility1);
        dao.saveFacility(facility2);
        dao.saveFacility(facility3);

        // then
        assertEquals(sizeAtStart + 3, dataStore.size());
        assertEquals(facility1.getId() + 1, facility2.getId());
        assertEquals(facility2.getId() + 1, facility3.getId());
        assertTrue(dataStore.containsValue(facility1));
        assertTrue(dataStore.containsValue(facility2));
        assertTrue(dataStore.containsValue(facility3));
    }

    @Test
    @Order(4)
    public void givenOldFacility_whenSaveFacility_thenUpdateData() {
        // given
        int sizeAtStart = dataStore.size();
        Facility facility1 = new Building();
        dao.saveFacility(facility1);

        Facility facility = new Building();
        facility.setId(facility1.getId());

        // when
        dao.saveFacility(facility);

        // then
        assertEquals(sizeAtStart + 1, dataStore.size());
        assertEquals(facility1.getId(), facility.getId());
        assertEquals(facility, dataStore.get(facility.getId()));
    }

    @Test
    @Order(5)
    public void givenNewFacilityWithId_whenSaveFacility_thenThrowException() {
        // given
        int sizeAtStart = dataStore.size();
        Facility facility1 = new Building();
        facility1.setId(1000);

        // when
        // then
        assertThrows(DataAccessException.class, () -> dao.saveFacility(facility1));
        assertEquals(sizeAtStart, dataStore.size());
    }

    @Test
    @Order(4)
    public void givenOldFacilityWithDifferentType_whenSaveFacility_thenThrowException() {
        // given
        int sizeAtStart = dataStore.size();
        Facility building = new Building();
        dao.saveFacility(building);

        Facility unit = new Unit((Building)building);
        unit.setId(building.getId());

        // when
        // then
        assertThrows(DataAccessException.class, () -> dao.saveFacility(unit));
        assertEquals(sizeAtStart + 1, dataStore.size());
    }

    @Test
    @Order(6)
    public void givenFacilities_whenFindAllFacilities_thenReturnThemAsList() {
        assertFalse(dataStore.isEmpty());

        // when
        List<Facility> facilities = dao.findAllFacilities();

        // then
        assertEquals(dataStore.size(), facilities.size());
        dataStore.values().forEach(fac -> assertTrue(facilities.contains(fac)));
    }

    @Test
    @Order(1)
    public void givenEmptyStore_whenFindAllFacilities_thenReturnEmptyList() {
        // given
        assertTrue(dataStore.isEmpty());
        // when
        List<Facility> facilities = dao.findAllFacilities();

        // then
        assertTrue(facilities.isEmpty());
    }

    @Test
    @Order(7)
    public void givenIdOfExistingFacility_whenFindFacilityById_thenReturnFacility() {
        // given
        assertFalse(dataStore.isEmpty());
        Set<Long> ids = dataStore.keySet();

        ids.forEach(id -> {
            assertEquals(dataStore.get(id), dao.findFacilityById(id));
        });

    }

    @Test
    @Order(8)
    public void givenIdOfNotExistingFacility_whenFindFacilityById_thenReturnNull() {
        // given
        Set<Long> ids = dataStore.keySet();
        assertFalse(ids.contains(1002L));

        // when
        Facility fac = dao.findFacilityById(1002);

        // then
        assertNull(fac);
    }

    @Test
    @Order(9)
    public void givenNotExistingFacility_whenRemoveFacility_thenDoNothing() {
        // given
        int sizeAtStart = dataStore.size();
        assertFalse(dataStore.keySet().contains(1005L));
        Facility face = new Unit(new Building());
        face.setId(1005L);

        // when
        dao.removeFacility(1005L);

        // then
        assertEquals(sizeAtStart, dataStore.size());

    }

    @Test
    @Order(10)
    public void givenExistingFacility_whenRemoveFacility_thenFacilityRemoved() {
        // given
        int sizeAtStart = dataStore.size();
        assertFalse(dataStore.isEmpty());
        Facility face = (Facility) dataStore.values().toArray()[0];

        // when
        dao.removeFacility(face.getId());

        // then
        assertEquals(sizeAtStart - 1, dataStore.size());
        assertFalse(dataStore.containsValue(face));

    }

    @Test
    @Order(11)
    public void givenBuildingWithUnits_whenRemoveBuilding_thenUnitsRemoved() {
        // given
        Building building = new Building();
        dao.saveFacility(building);
        Unit unit1 = new Unit(building);
        building.addUnit(unit1);
        dao.saveFacility(unit1);
        Unit unit2 = new Unit(building);
        building.addUnit(unit2);
        dao.saveFacility(unit2);

        int sizeAtStart = dataStore.size();

        // when
        dao.removeFacility(building.getId());

        // then
        assertEquals(sizeAtStart - 3, dataStore.size());
        assertFalse(dataStore.containsValue(building));
        assertFalse(dataStore.containsValue(unit1));
        assertFalse(dataStore.containsValue(unit2));
        assertTrue(building.getUnits().isEmpty());
        assertNull(unit1.getBuilding());
        assertNull(unit2.getBuilding());
    }

    @Test
    @Order(11)
    public void givenBuildingWithUnits_whenRemoveUnit_thenUnitRemovedFromBuilding() {
        // given
        Building building = new Building();
        dao.saveFacility(building);
        Unit unit1 = new Unit(building);
        building.addUnit(unit1);
        dao.saveFacility(unit1);
        Unit unit2 = new Unit(building);
        building.addUnit(unit2);
        dao.saveFacility(unit2);

        int sizeAtStart = dataStore.size();

        // when
        dao.removeFacility(unit1.getId());

        // then
        assertEquals(sizeAtStart - 1, dataStore.size());
        assertTrue(dataStore.containsValue(building));
        assertFalse(dataStore.containsValue(unit1));
        assertFalse(building.getUnits().contains(unit1));
    }
}