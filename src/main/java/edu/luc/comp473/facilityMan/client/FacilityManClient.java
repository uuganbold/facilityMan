package edu.luc.comp473.facilityMan.client;

import edu.luc.comp473.facilityMan.business.service.information.FacilityInformationService;
import edu.luc.comp473.facilityMan.business.service.inspection.FacilityInspectionService;
import edu.luc.comp473.facilityMan.business.service.inventory.FacilityInventoryService;
import edu.luc.comp473.facilityMan.business.service.maintenance.MaintenanceService;
import edu.luc.comp473.facilityMan.business.service.request.MaintenanceRequestService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class FacilityManClient {
    public static void main(String[] args) throws Exception{
        ApplicationContext context = new ClassPathXmlApplicationContext("META-INF/app-context.xml");
        System.out.println("***************** Application Context instantiated! ******************");

        FacilityInformationService facilityInformationService = (FacilityInformationService) context.getBean("facilityInformationService");
        FacilityInspectionService facilityInspectionService = (FacilityInspectionService) context.getBean("facilityInspectionService");
        FacilityInventoryService facilityInventoryService = (FacilityInventoryService) context.getBean("facilityInventoryService");
        MaintenanceService maintenanceService = (MaintenanceService) context.getBean("maintenanceService");
        MaintenanceRequestService maintenanceRequestService = (MaintenanceRequestService) context.getBean("maintenanceRequestService");
    }
}
