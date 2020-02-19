package edu.luc.comp473.facilityMan.business.entities.inspection;

import edu.luc.comp473.facilityMan.business.entities.facility.Facility;
import edu.luc.comp473.facilityMan.business.entities.util.Schedule;
import edu.luc.comp473.facilityMan.business.entities.util.Status;

/**
 * Entity for facility inspection.
 */
public class FacilityInspection {
    private InspectionType inspectionType;
    private Schedule schedule;
    private Status status;
    private Facility facility;

    /**
     * When we use ORM framework we are not likely to have this constructor. It can
     * be fixed later.
     *
     * @param inspectionType
     * @param schedule
     * @param facility
     */
    public FacilityInspection(InspectionType inspectionType, Schedule schedule, Facility facility) {
        this.inspectionType = inspectionType;
        this.schedule = schedule;
        this.facility = facility;
        this.status = Status.SCHEDULED;
    }

    public InspectionType getInspectionType() {
        return inspectionType;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public Facility getFacility() {
        return facility;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}