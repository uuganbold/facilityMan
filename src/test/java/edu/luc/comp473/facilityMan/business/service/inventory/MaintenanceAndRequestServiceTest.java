package edu.luc.comp473.facilityMan.business.service.inventory;

import edu.luc.comp473.facilityMan.business.entities.facility.Building;
import edu.luc.comp473.facilityMan.business.entities.facility.Facility;
import edu.luc.comp473.facilityMan.business.entities.maintenance.Maintenance;
import edu.luc.comp473.facilityMan.business.entities.maintenance.MaintenanceOrder;
import edu.luc.comp473.facilityMan.business.entities.maintenance.MaintenanceRequest;
import edu.luc.comp473.facilityMan.business.entities.maintenance.Problem;
import edu.luc.comp473.facilityMan.business.entities.util.Schedule;
import edu.luc.comp473.facilityMan.business.service.maintenance.MaintenanceService;
import edu.luc.comp473.facilityMan.business.service.maintenance.MaintenanceServiceImpl;
import edu.luc.comp473.facilityMan.business.service.request.MaintenanceRequestService;
import edu.luc.comp473.facilityMan.business.service.request.MaintenanceRequestServiceImpl;
import edu.luc.comp473.facilityMan.persistence.inventory.maintenance.HashMapMaintenanceDao;
import edu.luc.comp473.facilityMan.persistence.inventory.maintenance.MaintenanceDao;
import org.junit.jupiter.api.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test cases regarding MaintenanceService and MaintenanceRequestService
 */

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class MaintenanceAndRequestServiceTest {

    private HashMap<Long, Maintenance> dataStore;
    private MaintenanceDao dao;
    private MaintenanceService maintenanceService;
    private MaintenanceRequestService maintenanceRequestService;

    @BeforeAll
    public void setUp(){
        dataStore = new HashMap<>();
        dao = new HashMapMaintenanceDao(dataStore);
        maintenanceService = new MaintenanceServiceImpl(dao);
        maintenanceRequestService = new MaintenanceRequestServiceImpl(dao);
    }

    @Test
    @Order(2)
    public void scheduleMaintenanceTest(){
        assertTrue(dataStore.isEmpty());
        Maintenance maintenance = generateNewMaintenance(1);

        maintenanceService.scheduleMaintenance(maintenance);

        assertEquals(1, dataStore.size());
        assertTrue(dataStore.containsValue(maintenance));
    }

    @Test
    @Order(3)
    public void listMaintenanceTest(){
        Maintenance maintenance1 = generateNewMaintenance(1);
        Maintenance maintenance2 = generateNewMaintenance(2);
        Maintenance maintenance3 = generateNewMaintenance(3);

        dao.addMaintenance(maintenance1);
        dao.addMaintenance(maintenance2);
        dao.addMaintenance(maintenance3);

        List<Maintenance> allMaintenance = maintenanceService.listMaintenance();

        assertEquals(maintenance1, allMaintenance.get(0));
        assertEquals(maintenance2, allMaintenance.get(1));
        assertEquals(maintenance3, allMaintenance.get(2));
    }

    @Test
    @Order(4)
    public void whenCalcMaintCostForFacilityTest(){
        Maintenance maintenance = generateNewMaintenance(1);

        MaintenanceOrder maintenanceOrder1 = new MaintenanceOrder(new BigDecimal(1), "");
        MaintenanceOrder maintenanceOrder2 = new MaintenanceOrder(new BigDecimal(1), "");
        MaintenanceOrder maintenanceOrder3 = new MaintenanceOrder(new BigDecimal(1), "");

        dao.addMaintenance(maintenance);
        BigDecimal actual = maintenanceService.calcMaintenanceCostForFacility(1);

        assertEquals(0, actual.intValue());

        maintenance.addOrder(maintenanceOrder1);
        maintenance.addOrder(maintenanceOrder2);
        maintenance.addOrder(maintenanceOrder3);

        dao.addMaintenance(maintenance);

        actual = maintenanceService.calcMaintenanceCostForFacility(1);

        assertEquals(3, actual.intValue());
    }

    @Test
    @Order(5)
    public void calcDownTimeForFacilityTest(){
        Date startDate = new Date(2020, 01, 01);
        Date endDate = new Date(2020, 02, 01);

        long expected = TimeUnit.DAYS.convert(endDate.getTime() - startDate.getTime(), TimeUnit.MILLISECONDS);

        Maintenance maintenance = generateNewMaintenance(1);
        maintenance.setSchedule(new Schedule(startDate, endDate));

        dao.addMaintenance(maintenance);

        int actual = maintenanceService.calcDownTimeForFacility(1);
        assertEquals(expected, actual);
    }

    @Test
    @Order(6)
    public void calcProblemRateForFacilityTest(){
        Maintenance maintenance = generateNewMaintenance(1);

        Problem problem1 = new Problem("");
        Problem problem2 = new Problem("");
        Problem problem3 = new Problem("");

        maintenance.addProblem(problem1);
        maintenance.addProblem(problem2);
        maintenance.addProblem(problem3);

        dao.addMaintenance(maintenance);

        int actual = maintenanceService.calcProblemRateForFacility(1);

        assertEquals(3, actual);
    }

    @Test
    @Order(7)
    public void listFacilityProblemsTest(){
        Maintenance maintenance = generateNewMaintenance(1);

        Problem problem1 = new Problem("");
        Problem problem2 = new Problem("");
        Problem problem3 = new Problem("");

        maintenance.addProblem(problem1);
        maintenance.addProblem(problem2);
        maintenance.addProblem(problem3);

        dao.addMaintenance(maintenance);

        List<Problem> allProblems = maintenanceService.listFacilityProblems(1);

        assertEquals(problem1, allProblems.get(0));
        assertEquals(problem2, allProblems.get(1));
        assertEquals(problem3, allProblems.get(2));
    }

    @Test
    @Order(8)
    public void whenMakeMaintenanceRequest_thenMakeIt() {
        Maintenance maintenance = generateNewMaintenance(1);
        dao.addMaintenance(maintenance);

        assertTrue(maintenance.getRequests().isEmpty());
        maintenanceRequestService.makeMaintenanceRequest(new Problem(""), maintenance.getId());
        assertEquals(1, maintenance.getRequests().size());
    }

    @Test
    @Order(9)
    public void whenListMaintenanceRequest_thenListThem(){
        MaintenanceRequest maintenanceRequest1 = new MaintenanceRequest(1, "");
        MaintenanceRequest maintenanceRequest2 = new MaintenanceRequest(2, "");
        MaintenanceRequest maintenanceRequest3 = new MaintenanceRequest(3, "");

        Maintenance maintenance = generateNewMaintenance(1);
        maintenance.addRequest(maintenanceRequest1);
        maintenance.addRequest(maintenanceRequest2);
        maintenance.addRequest(maintenanceRequest3);

        dao.addMaintenance(maintenance);

        List<MaintenanceRequest> maintenanceRequests = maintenanceRequestService.listMaintRequests();
        assertEquals(maintenanceRequest1, maintenanceRequests.get(0));
        assertEquals(maintenanceRequest2, maintenanceRequests.get(1));
        assertEquals(maintenanceRequest3, maintenanceRequests.get(2));
    }

    private Maintenance generateNewMaintenance(long id){
        Facility facility = new Building();
        facility.setId(id);
        Schedule schedule = new Schedule(new Date(1), new Date(2));
        return new Maintenance(schedule, facility);
    }
}