package edu.luc.comp473.facilityMan.business.facility;

public class Unit extends Facility {

    private int capacity;

    public Unit(long id, int capacity) {
        super(id);
        this.capacity = capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public int getCapacity() {
        return capacity;
    }
}
