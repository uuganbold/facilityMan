package edu.luc.comp473.facilityMan.business.entities.inspection;

import edu.luc.comp473.facilityMan.business.entities.facility.Facility;
import edu.luc.comp473.facilityMan.business.entities.util.Schedule;
import edu.luc.comp473.facilityMan.business.entities.util.Status;

/**
 * Entity for facility inspection.
 */
public class FacilityInspection {
    private long id;
    private InspectionType inspectionType;
    private Schedule schedule;
    private Status status;
    private long facilityId;

    public FacilityInspection(){
        
    }
    public FacilityInspection(InspectionType type, Schedule schedule2, Facility facility) {
        this.inspectionType=type;
        this.schedule=schedule2;
        facilityId=facility.getId();
	}

	public long getId() {
        return id;
    }

    public void setId(Long id){ this.id = id; }

    public InspectionType getInspectionType() {
        return inspectionType;
    }

    public void setInspectionType(InspectionType inspectionType){ this.inspectionType = inspectionType; }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule){ this.schedule = schedule; }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Long getFacility() { return facilityId; }

    public void setFacility(Long facilityId){ this.facilityId = facilityId; }

    @Override
    public String toString() {
        return "FacilityInspection [facilityId=" + facilityId + ", id=" + id + ", inspectionType=" + inspectionType
                + ", schedule=" + schedule + ", status=" + status + "]";
    }

}
