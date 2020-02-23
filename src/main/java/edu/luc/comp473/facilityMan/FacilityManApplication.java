package edu.luc.comp473.facilityMan;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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

	}

}
