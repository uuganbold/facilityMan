package edu.luc.comp473.facilityMan.business.entities.facility;

/**
 * Facility is the main object the system manages. All Facilities should extend
 * this class.
 */
public abstract class Facility {

    private long id;

    private FacilityDetail detail;

    public long getId() { return id; }

    public void setId(long id) { this.id = id; }

    public abstract int getCapacity();

    public FacilityDetail getDetail() {
        return detail;
    }

    public void setDetail(FacilityDetail detail) {
        this.detail = detail;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " [id=" + id + ", capacity=" + getCapacity() + ", detail=" + detail + "]";
    }

}
