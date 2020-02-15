package edu.luc.comp473.facilityMan.business.facility;

import lombok.Getter;

@Getter
public class Unit extends Facility {
    private int capacity;

    public int getCapacity() {
        return capacity;
    }
}