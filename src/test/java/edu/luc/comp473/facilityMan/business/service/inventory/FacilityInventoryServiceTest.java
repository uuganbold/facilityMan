package edu.luc.comp473.facilityMan.business.service.inventory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import edu.luc.comp473.facilityMan.business.entities.facility.Building;
import edu.luc.comp473.facilityMan.business.entities.facility.Facility;
import edu.luc.comp473.facilityMan.business.entities.facility.Unit;
import edu.luc.comp473.facilityMan.persistence.inventory.facility.FacilityDao;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import edu.luc.comp473.facilityMan.business.exceptions.DataAccessException;
import edu.luc.comp473.facilityMan.business.exceptions.DuplicatedEntityException;

/**
 * Test cases regarding FacilityInventoryService.
 */
public class FacilityInventoryServiceTest {

    @Test
    public void givenFacility_whenAddNewFacility_thenSaveToDataBase() {
        // given
        FacilityDao dao = Mockito.mock(FacilityDao.class);
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
        FacilityDao dao = Mockito.mock(FacilityDao.class);
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
        FacilityDao dao = Mockito.mock(FacilityDao.class);
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
        FacilityDao dao = Mockito.mock(FacilityDao.class);
        FacilityInventoryService service = new FacilityInventoryServiceImpl(dao);
        List<Facility> list = new ArrayList<>();
        list.add(new Building());
        list.add(new Unit(new Building()));

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
        FacilityDao dao = Mockito.mock(FacilityDao.class);
        FacilityInventoryService service = new FacilityInventoryServiceImpl(dao);

        Building building = new Building();
        building.setId(10);
        Unit unit = new Unit(building);
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
        FacilityDao dao = Mockito.mock(FacilityDao.class);
        FacilityInventoryService service = new FacilityInventoryServiceImpl(dao);

        when(dao.findFacilityById(anyLong())).thenReturn(null);

        // when
        Facility actual = service.getFacility(10);
        // then
        verify(dao, times(1)).findFacilityById(anyLong());
        assertNull(actual);
    }

    @Test
    public void givenDataAccessException_whenRemoveFacility_thenReturnFalse() {
        // given
        FacilityDao dao = Mockito.mock(FacilityDao.class);
        FacilityInventoryService service = new FacilityInventoryServiceImpl(dao);

        when(dao.findFacilityById(anyLong())).thenReturn(new Building());
        doThrow(DataAccessException.class).when(dao).removeFacility(any(Facility.class).getId());

        // when
        boolean removed = service.removeFacility(10L);

        // then
        verify(dao).removeFacility(any(Facility.class).getId());
        assertFalse(removed);
    }

    @Test
    public void givenFacilityNotExists_whenRemoveFacility_thenReturnFalse() {
        // given
        FacilityDao dao = Mockito.mock(FacilityDao.class);
        FacilityInventoryService service = new FacilityInventoryServiceImpl(dao);

        when(dao.findFacilityById(anyLong())).thenReturn(null);

        // when
        boolean removed = service.removeFacility(10L);

        // then
        verify(dao, times(0)).removeFacility(any(Facility.class).getId());
        verify(dao).findFacilityById(10L);
        assertFalse(removed);
    }

    @Test
    public void givenFacility_whenRemoveFacility_thenRemoveItAndReturnTrue() {
        // given
        FacilityDao dao = Mockito.mock(FacilityDao.class);
        FacilityInventoryService service = new FacilityInventoryServiceImpl(dao);

        Facility facility = new Building();
        facility.setId(20);

        when(dao.findFacilityById(facility.getId())).thenReturn(facility);

        ArgumentCaptor<Facility> captor = ArgumentCaptor.forClass(Facility.class);
        doNothing().when(dao).removeFacility(captor.capture().getId());

        // when
        boolean removed = service.removeFacility(facility.getId());

        // then
        assertEquals(facility, captor.getValue());
        verify(dao).findFacilityById(facility.getId());
        verify(dao).removeFacility(facility.getId());
        assertTrue(removed);
    }
}