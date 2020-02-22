package edu.luc.comp473.facilityMan.business.entities.use;

import edu.luc.comp473.facilityMan.business.entities.facility.Facility;
import edu.luc.comp473.facilityMan.business.entities.util.Schedule;

/**
 * Entity for facility use.
 */
public class FacilityUse {

    private long id;
    private Facility facility;
    private Schedule schedule;

    public Facility getFacility() {
        return facility;
    }

    public void setFacility(Facility facility) {
        this.facility = facility;
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

}
