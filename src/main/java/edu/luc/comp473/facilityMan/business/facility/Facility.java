package edu.luc.comp473.facilityMan.business.facility;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Facility {
    protected Long id;
    protected FacilityDetail detail;

    public abstract int getCapacity();
}