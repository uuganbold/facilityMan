package edu.luc.comp473.facilityMan;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import edu.luc.comp473.facilityMan.business.service.information.FacilityInformationService;
import edu.luc.comp473.facilityMan.business.service.inspection.FacilityInspectionService;
import edu.luc.comp473.facilityMan.business.service.inventory.FacilityInventoryService;
import edu.luc.comp473.facilityMan.business.service.maintenance.MaintenanceService;
import edu.luc.comp473.facilityMan.business.service.request.MaintenanceRequestService;
import edu.luc.comp473.facilityMan.business.service.use.FacilityUseService;

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

	@Override
	public void run(String... args) throws Exception {

	}

}
