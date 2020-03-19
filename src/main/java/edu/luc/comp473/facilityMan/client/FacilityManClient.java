package edu.luc.comp473.facilityMan.client;

import edu.luc.comp473.facilityMan.business.entities.facility.Building;
import edu.luc.comp473.facilityMan.business.entities.facility.Facility;
import edu.luc.comp473.facilityMan.business.entities.facility.Unit;
import edu.luc.comp473.facilityMan.business.entities.inspection.FacilityInspection;
import edu.luc.comp473.facilityMan.business.entities.inspection.InspectionType;
import edu.luc.comp473.facilityMan.business.entities.maintenance.Maintenance;
import edu.luc.comp473.facilityMan.business.entities.maintenance.MaintenanceRequest;
import edu.luc.comp473.facilityMan.business.entities.maintenance.Problem;
import edu.luc.comp473.facilityMan.business.entities.use.FacilityUse;
import edu.luc.comp473.facilityMan.business.entities.util.Schedule;
import edu.luc.comp473.facilityMan.business.entities.util.Status;
import edu.luc.comp473.facilityMan.business.service.information.FacilityInformationService;
import edu.luc.comp473.facilityMan.business.service.inspection.FacilityInspectionService;
import edu.luc.comp473.facilityMan.business.service.inventory.FacilityInventoryService;
import edu.luc.comp473.facilityMan.business.service.maintenance.MaintenanceService;
import edu.luc.comp473.facilityMan.business.service.request.MaintenanceRequestService;
import edu.luc.comp473.facilityMan.business.service.use.FacilityUseService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;

public class FacilityManClient {
    public static void main(String[] args) throws Exception{
        ApplicationContext context = new ClassPathXmlApplicationContext("app-context.xml");
        System.out.println("***************** Application Context instantiated! ******************");

        FacilityInformationService facilityInformationService = (FacilityInformationService) context.getBean("facilityInformationService");
        FacilityInspectionService facilityInspectionService = (FacilityInspectionService) context.getBean("facilityInspectionService");
        FacilityInventoryService facilityInventoryService = (FacilityInventoryService) context.getBean("facilityInventoryService");
        FacilityUseService facilityUseService = (FacilityUseService) context.getBean("facilityUseService");
        MaintenanceService maintenanceService = (MaintenanceService) context.getBean("maintenanceService");
        MaintenanceRequestService maintenanceRequestService = (MaintenanceRequestService) context.getBean("maintenanceRequestService");

        // unit 1
        Unit unit1 = (Unit) context.getBean("unit");
        unit1.setId(1L);
        unit1.setCapacity(1);
        unit1.getDetail().setName("unit1 detail name");
        unit1.getDetail().setDescription("unit1 detail description");

        // building 1
        Building building1 = (Building) context.getBean("building");
        building1.setId(2L);
        building1.addUnit(unit1);
        building1.getDetail().setName("building1 detail name");
        building1.getDetail().setDescription("building1 detail description");

        // schedule 1
        Schedule schedule1 = (Schedule) context.getBean("schedule");

        // dates
        Date startDate = schedule1.getStartDate();
        Date endDate = schedule1.getEndDate();
        startDate.setTime(1L);
        endDate.setTime(3600000);

        // inspection
        FacilityInspection facilityInspection1 = (FacilityInspection) context.getBean("facilityInspection");
        facilityInspection1.setFacility(unit1.getId());
        facilityInspection1.setId(facilityInspection1.getFacility());
        facilityInspection1.setInspectionType(InspectionType.FIRE);
        facilityInspection1.setStatus(Status.SCHEDULED);
        facilityInspection1.setSchedule(schedule1);

        // facility inventory
        facilityInventoryService.addNewFacility(unit1);
        facilityInventoryService.addNewFacility(building1);
        System.out.println("Facilities:\n");
        for (Facility f: facilityInventoryService.listFacilities()){
            System.out.println("\t" + f + "\n");
        }

        // facility information
        String s = facilityInformationService.getFacilityInformation(1L).getName();
        System.out.println("Facility Information:\n");
        System.out.println("\t" + s + "\n");

        // facility use
        facilityUseService.assignFacilityToUse(building1.getId(), schedule1);
        System.out.println("Facility Uses:\n");
        for(FacilityUse use : facilityUseService.listActualUsage()){
            System.out.println("\t" + use + "\n");
        }

        // facility inspection
        facilityInspectionService.addInspection(facilityInspection1);
        System.out.println("Inspections:\n");
        for(FacilityInspection f : facilityInspectionService.listInspections()){
            System.out.println("\t" + f + "\n");
        }

        // maintenance
        Maintenance maintenance = (Maintenance) context.getBean("maintenance");
        maintenanceService.scheduleMaintenance(maintenance);
        System.out.println("Maintenance:\n");
        for(Maintenance m : maintenanceService.listMaintenance()){
            System.out.println("\t" + m + "\n");
        }

        // maintenance request
        Problem problem = (Problem) context.getBean("problem");
        problem.setDescription("it's a problem!");
        maintenanceRequestService.makeMaintenanceRequest(problem, maintenance.getId());
        System.out.println("Maintenance Requests:\n");
        for(MaintenanceRequest m : maintenanceRequestService.listMaintRequests()) {
            System.out.println("\t" + m + "\n");
        }
    }
}
