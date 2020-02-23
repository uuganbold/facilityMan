package edu.luc.comp473.facilityMan.business.entities.use;

import edu.luc.comp473.facilityMan.business.entities.util.Schedule;

/**
 * Entity for facility use.
 */
public class FacilityUse {

    private long id;
    private long facilityId;
    private Schedule schedule;

    public long getFacility() {
        return facilityId;
    }

    public void setFacility(long id) {
        this.facilityId = id;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "FacilityUse [facilityId=" + facilityId + ", id=" + id + ", schedule=" + schedule + "]";
    }

}
