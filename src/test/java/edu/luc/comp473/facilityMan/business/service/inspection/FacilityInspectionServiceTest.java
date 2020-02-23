package edu.luc.comp473.facilityMan.business.service.inspection;

import edu.luc.comp473.facilityMan.business.entities.facility.Building;
import edu.luc.comp473.facilityMan.business.entities.facility.Facility;
import edu.luc.comp473.facilityMan.business.entities.inspection.FacilityInspection;
import edu.luc.comp473.facilityMan.business.entities.inspection.InspectionType;
import edu.luc.comp473.facilityMan.business.entities.util.Schedule;
import edu.luc.comp473.facilityMan.persistence.inspection.FacilityInspectionDao;
import edu.luc.comp473.facilityMan.persistence.inspection.HashMapFacilityInspectionDao;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FacilityInspectionServiceTest {
    private FacilityInspectionDao dao;
    private FacilityInspectionService service;

    @BeforeEach
    void setUp() {
        dao = new HashMapFacilityInspectionDao();
        service = new FacilityInspectionServiceImpl(dao);
    }

    @AfterEach
    void tearDown() {
        dao = null;
        service = null;
    }

    @Test
    public void addInspectionTest(){
        assertTrue(dao.getAllFacilityInspections().isEmpty());

        FacilityInspection facilityInspection = generateFacilityInspection(1);

        service.addInspection(facilityInspection);

        assertEquals(1, dao.getAllFacilityInspections().size());
        assertEquals(facilityInspection, dao.getAllFacilityInspections().get(0));

    }

    @Test
    public void listInspectionsTest(){
        FacilityInspection facilityInspection1 = generateFacilityInspection(1);
        FacilityInspection facilityInspection2 = generateFacilityInspection(2);
        FacilityInspection facilityInspection3 = generateFacilityInspection(3);

        assertTrue(dao.getAllFacilityInspections().isEmpty());

        dao.addFacilityInspection(facilityInspection1);
        dao.addFacilityInspection(facilityInspection2);
        dao.addFacilityInspection(facilityInspection3);

        List<FacilityInspection> allFacilityInspections = service.listInspections();

        assertEquals(3, allFacilityInspections.size());
        assertEquals(facilityInspection1, allFacilityInspections.get(0));
        assertEquals(facilityInspection2, allFacilityInspections.get(1));
        assertEquals(facilityInspection3, allFacilityInspections.get(2));
    }

    public FacilityInspection generateFacilityInspection(long id){
        InspectionType inspectionType = InspectionType.FIRE;
        Schedule schedule = new Schedule(new Date(1), new Date(2));
        Facility facility = new Building();
        facility.setId(id);
        FacilityInspection facilityInspection = new FacilityInspection(inspectionType, schedule, facility);
        return facilityInspection;
    }
}