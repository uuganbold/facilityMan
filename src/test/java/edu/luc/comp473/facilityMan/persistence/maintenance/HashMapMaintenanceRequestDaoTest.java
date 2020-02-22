package edu.luc.comp473.facilityMan.persistence.maintenance;

import edu.luc.comp473.facilityMan.business.entities.facility.Building;
import edu.luc.comp473.facilityMan.business.entities.facility.Facility;
import edu.luc.comp473.facilityMan.business.entities.maintenance.FacilityMaintenanceRequest;
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

class HashMapMaintenanceRequestDaoTest {
    MaintenanceRequestDao dao = Mockito.mock(MaintenanceRequestDao.class);
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

        ArgumentCaptor<FacilityMaintenanceRequest> captor = ArgumentCaptor.forClass(FacilityMaintenanceRequest.class);
        doNothing().when(dao).saveFacilityMaintenanceRequest(captor.capture());

        FacilityMaintenanceRequest fmr = service.makeFacilityMaintenanceReq(problem, facility.getId());

        assertEquals(fmr, captor.getValue());
    }

    @Test
    public void listMaintReqTest(){
        List<FacilityMaintenanceRequest> list = new ArrayList<>();
        list.add(new FacilityMaintenanceRequest((long) 1, ""));

        when(dao.findAllFacilityMaintenanceRequests()).thenReturn(list);

        List<FacilityMaintenanceRequest> actual = service.listMaintReq();
        verify(dao).findAllFacilityMaintenanceRequests();

        assertEquals(list, actual);
        assertEquals(1, actual.size());
    }
}