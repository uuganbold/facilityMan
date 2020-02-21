package edu.luc.comp473.facilityMan.business.entities.facility;

/**
 * Facility is the main object the system manages. All Facilities should extend
 * this class.
 */
public abstract class Facility {

    /**
     * Facility has unique identifier.
     */
    private long id;

    /**
     * Each facility should have details about that facility.
     */
    private FacilityDetail detail;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    /**
     * Each facility should have capacity.
     *
     * @return capacity of the facility
     */
    public abstract int getCapacity();

    public FacilityDetail getDetail() {
        return detail;
    }

    public void setDetail(FacilityDetail detail) {
        this.detail = detail;
    }
}
