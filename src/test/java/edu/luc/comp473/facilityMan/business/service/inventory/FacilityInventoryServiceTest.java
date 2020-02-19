package edu.luc.comp473.facilityMan.business.service.inventory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import edu.luc.comp473.facilityMan.business.entities.facility.Building;
import edu.luc.comp473.facilityMan.business.entities.facility.Facility;
import edu.luc.comp473.facilityMan.business.entities.facility.Unit;
import edu.luc.comp473.facilityMan.business.exceptions.DuplicatedEntityException;
import edu.luc.comp473.facilityMan.persistence.inventory.FacilityInventoryDao;

/**
 * Test cases regarding FacilityInventoryService.
 */
public class FacilityInventoryServiceTest {

    @Test
    public void givenFacility_whenAddNewFacility_thenSaveToDataBase() {
        // given
        FacilityInventoryDao dao = Mockito.mock(FacilityInventoryDao.class);
        FacilityInventoryService service = new FacilityInventoryServiceImpl(dao);
        Facility facility = new Building();

        ArgumentCaptor<Facility> captor = ArgumentCaptor.forClass(Facility.class);
        doNothing().when(dao).saveFacility(captor.capture());

        // when
        service.addNewFacility(facility);

        // then
        assertEquals(facility, captor.getValue());
    }

    @Test
    public void givenDuplicatedFacility_whenAddNewFacility_thenThrowDuplicatedEntityException() {
        // given
        FacilityInventoryDao dao = Mockito.mock(FacilityInventoryDao.class);
        FacilityInventoryService service = new FacilityInventoryServiceImpl(dao);
        Facility facility = new Building();
        facility.setId(10);

        when(dao.findFacilityById(facility.getId())).thenReturn(new Building());

        // when
        // then
        assertThrows(DuplicatedEntityException.class, () -> service.addNewFacility(facility));
    }

    @Test
    public void givenEmptyList_whenListFacilities_thenForwardEmptyList() {
        // given
        FacilityInventoryDao dao = Mockito.mock(FacilityInventoryDao.class);
        FacilityInventoryService service = new FacilityInventoryServiceImpl(dao);

        when(dao.findAllFacilities()).thenReturn(new ArrayList<Facility>());

        // when
        List<Facility> list = service.listFacilities();
        // then
        verify(dao).findAllFacilities();
        assertEquals(0, list.size());
    }

    @Test
    public void givenList_whenListFacilities_thenForwardList() {
        // given
        FacilityInventoryDao dao = Mockito.mock(FacilityInventoryDao.class);
        FacilityInventoryService service = new FacilityInventoryServiceImpl(dao);
        List<Facility> list = new ArrayList<>();
        list.add(new Building());
        list.add(new Unit());

        when(dao.findAllFacilities()).thenReturn(list);

        // when
        List<Facility> actual = service.listFacilities();
        // then
        verify(dao).findAllFacilities();
        assertEquals(list, actual);
        assertEquals(2, actual.size());
    }

    @Test
    public void givenFacility_whenGetFacilities_thenForwardFacility() {
        // given
        FacilityInventoryDao dao = Mockito.mock(FacilityInventoryDao.class);
        FacilityInventoryService service = new FacilityInventoryServiceImpl(dao);

        Building building = new Building();
        building.setId(10);
        Unit unit = new Unit();
        unit.setId(11);

        when(dao.findFacilityById(building.getId())).thenReturn(building);
        when(dao.findFacilityById(unit.getId())).thenReturn(unit);

        // when
        Facility actual10 = service.getFacility(10);
        Facility actual11 = service.getFacility(11);
        // then
        verify(dao, times(2)).findFacilityById(anyLong());
        assertEquals(actual10, building);
        assertEquals(actual11, unit);
    }

    @Test
    public void givenNullFacility_whenGetFacilities_thenForwardNull() {
        // given
        FacilityInventoryDao dao = Mockito.mock(FacilityInventoryDao.class);
        FacilityInventoryService service = new FacilityInventoryServiceImpl(dao);

        when(dao.findFacilityById(anyLong())).thenReturn(null);

        // when
        Facility actual = service.getFacility(10);
        // then
        verify(dao, times(1)).findFacilityById(anyLong());
        assertNull(actual);
    }
}