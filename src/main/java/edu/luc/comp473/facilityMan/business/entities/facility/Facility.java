package edu.luc.comp473.facilityMan.business.entities.facility;

public abstract class Facility {
    private FacilityDetail detail;
    private long id;

    public Facility(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public abstract int getCapacity();

    public FacilityDetail getDetail() {
        return detail;
    }

    public void setDetail(FacilityDetail detail) {
        this.detail = detail;
    }
}
