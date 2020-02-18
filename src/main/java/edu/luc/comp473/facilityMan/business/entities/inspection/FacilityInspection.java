package edu.luc.comp473.facilityMan.business.entities.inspection;

import edu.luc.comp473.facilityMan.business.entities.facility.Facility;
import edu.luc.comp473.facilityMan.business.entities.util.Schedule;
import edu.luc.comp473.facilityMan.business.entities.util.Status;

public class FacilityInspection {
    private InspectionType inspectionType;
    private Schedule schedule;
    private Status status;
    private Facility facility;

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
