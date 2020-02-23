package edu.luc.comp473.facilityMan.business.service.information;

import edu.luc.comp473.facilityMan.business.entities.facility.Building;
import edu.luc.comp473.facilityMan.business.entities.facility.Facility;
import edu.luc.comp473.facilityMan.business.entities.facility.FacilityDetail;
import edu.luc.comp473.facilityMan.business.entities.facility.Unit;
import edu.luc.comp473.facilityMan.persistence.facility.FacilityDao;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Test cases regarding FacilityInformationService.
 */
public class FacilityInformationServiceTest {

    @Test
    public void givenFacility_getDetailName() {
        // given
        Facility facility = Mockito.mock(Facility.class);
        FacilityDetail fd = new FacilityDetail();
        fd.setName("Name");

        // when
        when(facility.getDetail()).thenReturn(fd);

        // then
        assertEquals("Name", facility.getDetail().getName());
    }

    @Test
    public void givenFacility_AddDetailsandVerify() {
        // given
        FacilityDao dao = Mockito.mock(FacilityDao.class);
        FacilityInformationService service = new FacilityInformationServiceImpl(dao);
        Facility facility = new Building();
        facility.setId(10L);
        FacilityDetail fd = new FacilityDetail();
        fd.setName("Name");

        when(dao.findFacilityById(facility.getId())).thenReturn(facility);

        service.addFacilityDetail(10L, fd);

        assertEquals("Name", service.getFacilityInformation(10L).getName());
    }

    @Test
    public void givenUnitBuilding_AddCapacityandVerify() {
        // given
        FacilityDao dao = Mockito.mock(FacilityDao.class);
        FacilityInformationService service = new FacilityInformationServiceImpl(dao);

        Building building = new Building();
        building.setId(1L);
        Unit unit1 = new Unit(building);
        unit1.setId(2L);
        unit1.setCapacity(10);
        Unit unit2 = new Unit(building);
        unit2.setId(3L);
        unit2.setCapacity(20);
        building.addUnit(unit1);
        building.addUnit(unit2);

        when(dao.findFacilityById(building.getId())).thenReturn(building);
        when(dao.findFacilityById(unit1.getId())).thenReturn(unit1);
        when(dao.findFacilityById(unit2.getId())).thenReturn(unit2);

        assertEquals(10, service.requestAvailableCapacity(2L));
        assertEquals(20, service.requestAvailableCapacity(3L));
        assertEquals(30, service.requestAvailableCapacity(1L));
    }
}