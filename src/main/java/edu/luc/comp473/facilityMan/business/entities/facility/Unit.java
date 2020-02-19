package edu.luc.comp473.facilityMan.business.entities.facility;

/**
 * One type of facility. It is primitive pease in the building.
 */
public class Unit extends Facility {

    /**
     * Unit has its capacity.
     */
    private int capacity;

    /**
     * Unit should belong to specifc building.
     */
    private Building building;

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public int getCapacity() {
        return capacity;
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

}
