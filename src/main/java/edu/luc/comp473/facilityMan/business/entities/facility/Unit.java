package edu.luc.comp473.facilityMan.business.entities.facility;

/**
 * One type of primitive facility.
 */
public class Unit extends Facility {

    /**
     * Unit has its capacity.
     */
    private int capacity;
    private Building building;

    public Unit(Building building){
        this.building = building;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public int getCapacity() {
        return capacity;
    }

    public Building getBuilding() { return building; }

    public void setBuilding(Building building){ this.building = building;}
}
