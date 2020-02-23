package edu.luc.comp473.facilityMan;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import edu.luc.comp473.facilityMan.business.entities.facility.Building;
import edu.luc.comp473.facilityMan.business.entities.facility.Facility;
import edu.luc.comp473.facilityMan.business.entities.facility.FacilityDetail;
import edu.luc.comp473.facilityMan.business.entities.facility.Unit;
import edu.luc.comp473.facilityMan.business.entities.inspection.FacilityInspection;
import edu.luc.comp473.facilityMan.business.entities.inspection.InspectionType;
import edu.luc.comp473.facilityMan.business.entities.maintenance.Maintenance;
import edu.luc.comp473.facilityMan.business.entities.maintenance.MaintenanceOrder;
import edu.luc.comp473.facilityMan.business.entities.maintenance.MaintenanceRequest;
import edu.luc.comp473.facilityMan.business.entities.maintenance.Problem;
import edu.luc.comp473.facilityMan.business.entities.use.FacilityUse;
import edu.luc.comp473.facilityMan.business.entities.util.Schedule;
import edu.luc.comp473.facilityMan.business.service.information.FacilityInformationService;
import edu.luc.comp473.facilityMan.business.service.information.FacilityInformationServiceImpl;
import edu.luc.comp473.facilityMan.business.service.inspection.FacilityInspectionService;
import edu.luc.comp473.facilityMan.business.service.inspection.FacilityInspectionServiceImpl;
import edu.luc.comp473.facilityMan.business.service.inventory.FacilityInventoryService;
import edu.luc.comp473.facilityMan.business.service.inventory.FacilityInventoryServiceImpl;
import edu.luc.comp473.facilityMan.business.service.maintenance.MaintenanceService;
import edu.luc.comp473.facilityMan.business.service.maintenance.MaintenanceServiceImpl;
import edu.luc.comp473.facilityMan.business.service.request.MaintenanceRequestService;
import edu.luc.comp473.facilityMan.business.service.request.MaintenanceRequestServiceImpl;
import edu.luc.comp473.facilityMan.business.service.use.FacilityUseService;
import edu.luc.comp473.facilityMan.business.service.use.FacilityUseServiceImpl;
import edu.luc.comp473.facilityMan.persistence.facility.FacilityDao;
import edu.luc.comp473.facilityMan.persistence.facility.HashMapFacilityDao;
import edu.luc.comp473.facilityMan.persistence.inspection.HashMapFacilityInspectionDao;
import edu.luc.comp473.facilityMan.persistence.maintenance.HashMapMaintenanceDao;
import edu.luc.comp473.facilityMan.persistence.maintenance.MaintenanceDao;
import edu.luc.comp473.facilityMan.persistence.use.ArrayListUseDao;

@SpringBootApplication
public class FacilityManApplication implements CommandLineRunner {

	private final Logger logger = LoggerFactory.getLogger(FacilityManApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(FacilityManApplication.class, args);
	}

	private FacilityInformationService informationService;
	private FacilityInspectionService inspectionService;
	private FacilityInventoryService facilityService;
	private MaintenanceService maintenanceService;
	private MaintenanceRequestService requestService;
	private FacilityUseService useService;

	private void init() {
		FacilityDao facilityDao = new HashMapFacilityDao();
		facilityService = new FacilityInventoryServiceImpl(facilityDao);
		informationService = new FacilityInformationServiceImpl(facilityDao);
		inspectionService = new FacilityInspectionServiceImpl(new HashMapFacilityInspectionDao());
		MaintenanceDao maintenanceDao = new HashMapMaintenanceDao();
		maintenanceService = new MaintenanceServiceImpl(maintenanceDao);
		requestService = new MaintenanceRequestServiceImpl(maintenanceDao);
		useService = new FacilityUseServiceImpl(new ArrayListUseDao());
	}

	@Override
	public void run(String... args) throws Exception {
		init();
		facilityInventory();
		facilityDetail();
		facilityInspection();
		facilityMaintenance();
		facilityUse();
	}

	private void facilityUse() {
		useService.assignFacilityToUse(1L, new Schedule(new Date(2020, 1, 1), new Date(2020, 2, 15)));
		useService.assignFacilityToUse(2L, new Schedule(new Date(2020, 1, 1), new Date(2020, 2, 15)));
		List<FacilityUse> uses = useService.listActualUsage();
		uses.forEach(System.out::println);
	}

	private void facilityMaintenance() {
		Facility facility = facilityService.getFacility(1L);
		Maintenance maintenance = new Maintenance(new Schedule(new Date(2020, 2, 1), new Date(2020, 2, 3)), facility);
		maintenanceService.scheduleMaintenance(maintenance);
		Problem problem = new Problem("Some problem");
		MaintenanceRequest req = requestService.makeMaintenanceRequest(problem, 1L);
		MaintenanceOrder order = new MaintenanceOrder(new BigDecimal(1450), "To fix the problem");
		maintenance.addProblem(problem);
		maintenance.addRequest(req);
		maintenance.addOrder(order);

		facility = facilityService.getFacility(2L);
		maintenance = new Maintenance(new Schedule(new Date(2020, 3, 1), new Date(2020, 3, 3)), facility);
		maintenanceService.scheduleMaintenance(maintenance);

		List<Maintenance> maintenances = maintenanceService.listMaintenance();
		maintenances.forEach(System.out::println);
	}

	private void facilityInspection() {
		Facility facility = facilityService.getFacility(1L);
		inspectionService.addInspection(new FacilityInspection(InspectionType.FIRE,
				new Schedule(new Date(2020, 2, 1), new Date(2020, 2, 3)), facility));

		facility = facilityService.getFacility(2L);
		inspectionService.addInspection(new FacilityInspection(InspectionType.PEST,
				new Schedule(new Date(2020, 3, 2), new Date(2020, 3, 7)), facility));

		List<FacilityInspection> inspections = inspectionService.listInspections();
		inspections.forEach(System.out::println);

	}

	private void facilityInventory() {
		Building building = new Building();
		facilityService.addNewFacility(building);
		Unit unit = new Unit(building);
		unit.setCapacity(10);
		building.addUnit(unit);
		facilityService.addNewFacility(unit);

		unit = new Unit(building);
		unit.setCapacity(20);
		building.addUnit(unit);
		facilityService.addNewFacility(unit);

		List<Facility> facilities = facilityService.listFacilities();
		facilities.forEach(System.out::println);

		Facility facility = facilityService.getFacility(2L);
		System.out.println(facility);
	}

	private void facilityDetail() {
		informationService.addFacilityDetail(1L,
				new FacilityDetail("Cuneo", "Building located at center of the campus"));
		informationService.addFacilityDetail(2L, new FacilityDetail("212", "Lecture hall"));
		informationService.addFacilityDetail(3L, new FacilityDetail("313", "Lecture hall"));

		List<Facility> facilities = facilityService.listFacilities();
		facilities.forEach(System.out::println);

		System.out.println(informationService.getFacilityInformation(3L));
		System.out.println("Available capacity:" + informationService.requestAvailableCapacity(3L));

	}

}
