package edu.luc.comp473.facilityMan.business.facility;

import java.util.ArrayList;
import java.util.List;

public class Building extends Facility {

    private List<Unit> units = new ArrayList<>();

    public Building(long id) {
        super(id);
    }

    @Override
    public int getCapacity() {
        int totalCapacity = 0;
        for (Unit unit : units){
            totalCapacity += unit.getCapacity();
        }
        return totalCapacity;
    }

    public void addUnit(Unit unit){
        units.add(unit);
    }

    //Return true if unit is successfully removed, false otherwise
    public boolean removeUnit(Unit unit){
        if (units.contains(unit)){
            units.remove(unit);
            return true;
        }
        return false;
    }
}
