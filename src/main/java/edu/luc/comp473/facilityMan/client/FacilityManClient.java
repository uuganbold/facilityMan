package edu.luc.comp473.facilityMan.client;

import edu.luc.comp473.facilityMan.business.entities.facility.Building;
import edu.luc.comp473.facilityMan.business.entities.facility.Facility;
import edu.luc.comp473.facilityMan.business.entities.facility.Unit;
import edu.luc.comp473.facilityMan.business.entities.inspection.FacilityInspection;
import edu.luc.comp473.facilityMan.business.entities.util.Schedule;
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

        // date 1
        Date startDate, endDate;
        startDate = (Date) context.getBean("date");
        startDate.setTime(1L);
        endDate = (Date) context.getBean("date");
        endDate.setTime(2L);

        // schedule 1
        Schedule schedule1 = (Schedule) context.getBean("schedule");
        schedule1.setStartDate(startDate);
        schedule1.setEndDate(endDate);

        facilityInventoryService.addNewFacility(unit1);
        facilityInventoryService.addNewFacility(building1);
        for (Facility f: facilityInventoryService.listFacilities()){
            System.out.println(f + "\n");
        }
        String s = facilityInformationService.getFacilityInformation(1L).getName();
        System.out.println(s);

        facilityUseService.assignFacilityToUse(building1.getId(), schedule1);
    }
}
