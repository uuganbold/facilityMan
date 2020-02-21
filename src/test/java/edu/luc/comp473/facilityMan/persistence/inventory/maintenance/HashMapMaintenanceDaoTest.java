package edu.luc.comp473.facilityMan.persistence.inventory.maintenance;

import edu.luc.comp473.facilityMan.business.entities.facility.Building;
import edu.luc.comp473.facilityMan.business.entities.facility.Facility;
import edu.luc.comp473.facilityMan.business.entities.maintenance.Maintenance;
import edu.luc.comp473.facilityMan.business.entities.maintenance.MaintenanceRequest;
import edu.luc.comp473.facilityMan.business.entities.maintenance.Problem;
import edu.luc.comp473.facilityMan.business.entities.util.Schedule;
import edu.luc.comp473.facilityMan.business.service.maintenance.MaintenanceService;
import edu.luc.comp473.facilityMan.business.service.maintenance.MaintenanceServiceImpl;
import edu.luc.comp473.facilityMan.business.service.request.MaintenanceRequestService;
import edu.luc.comp473.facilityMan.business.service.request.MaintenanceRequestServiceImpl;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class HashMapMaintenanceDaoTest {

    private HashMap<Long, Maintenance> dataStore;

    private MaintenanceDao dao;

    @BeforeAll
    public void setUp(){
        dataStore = new HashMap<>();
        dao = new HashMapMaintenanceDao(dataStore);
    }

    //TODO edge conditions
    @Test
    @Order(2)
    public void givenEmptyStore_whenScheduleMaintenance_thenScheduleIt(){
        // given
        assertTrue(dataStore.isEmpty());
        Facility facility = new Building();
        facility.setId(1);
        Maintenance maintenance = new Maintenance(new Schedule(new Date(1), new Date(2)), facility);

        // when
        dao.addMaintenance(maintenance);

        // then
        assertEquals(1, dataStore.size());
        assertTrue(dataStore.containsValue(maintenance));
    }

    @Test
    @Order(3)
    public void givenMaintenance_whenMakeMaintenanceRequest_thenMakeIt() {
        Facility facility = new Building();
        facility.setId(1);
        Maintenance maintenance = new Maintenance(new Schedule(new Date(1), new Date(2)), facility);
        MaintenanceRequest maintenanceRequest = new MaintenanceRequest(maintenance.getId(), "");

        dao.addMaintenance(maintenance);

        assertTrue(maintenance.getRequests().isEmpty());
        dao.addMaintenanceRequest(maintenance.getId(), maintenanceRequest);
        assertEquals(1, maintenance.getRequests().size());
    }
}