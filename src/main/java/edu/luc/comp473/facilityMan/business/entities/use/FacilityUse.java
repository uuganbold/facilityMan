package edu.luc.comp473.facilityMan.business.entities.use;

import edu.luc.comp473.facilityMan.business.entities.facility.Facility;
import edu.luc.comp473.facilityMan.business.entities.util.Schedule;

public class FacilityUse {

    private Facility facility;
    private Schedule schedule;

    public FacilityUse(Facility facility, Schedule schedule) {
        this.facility = facility;
        this.schedule = schedule;
    }

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

}
