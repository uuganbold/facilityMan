package edu.luc.comp473.facilityMan.persistence.inventory.maintenance;

import edu.luc.comp473.facilityMan.business.entities.facility.Building;
import edu.luc.comp473.facilityMan.business.entities.facility.Facility;
import edu.luc.comp473.facilityMan.business.entities.maintenance.MaintenanceRequest;
import edu.luc.comp473.facilityMan.business.entities.maintenance.Problem;
import edu.luc.comp473.facilityMan.business.service.request.MaintenanceRequestService;
import edu.luc.comp473.facilityMan.business.service.request.MaintenanceRequestServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class HashMapMaintenanceDaoTest {
    MaintenanceDao dao = Mockito.mock(MaintenanceDao.class);
    MaintenanceRequestService service = new MaintenanceRequestServiceImpl(dao);


//    @BeforeEach
//    void setUp() {
//    }
//
//    @AfterEach
//    void tearDown() {
//    }

    //TODO edge conditions
    @Test
    public void givenFacilityMaintReq_whenMakeFacilityMaintReq_thenSaveToDatabase(){
        Problem problem = new Problem("");
        Facility facility = new Building();
        facility.setId(1);

        ArgumentCaptor<MaintenanceRequest> captor = ArgumentCaptor.forClass(MaintenanceRequest.class);
        doNothing().when(dao).addMaintenanceRequest(captor.capture());

        MaintenanceRequest fmr = service.makeMaintenanceReq(problem, facility.getId());

        assertEquals(fmr, captor.getValue());
    }

    @Test
    public void listMaintReqTest(){
        List<MaintenanceRequest> list = new ArrayList<>();
        list.add(new MaintenanceRequest((long) 1, ""));

        when(dao.getAllMaintenanceRequests()).thenReturn(list);

        List<MaintenanceRequest> actual = service.listMaintReq();
        verify(dao).getAllMaintenanceRequests();

        assertEquals(list, actual);
        assertEquals(1, actual.size());
    }
}