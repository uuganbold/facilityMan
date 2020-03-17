package edu.luc.comp473.facilityMan.business.entities.facility;

import java.util.ArrayList;
import java.util.List;

/**
 * Building is a construction constructed by Units.
 */
public class Building extends Facility {

    /**
     * A building is constructed by units.
     */
    private ArrayList<Unit> units = new ArrayList<>();

    /**
     * Building's capacity is defined by it's units' capacities.
     */
    @Override
    public int getCapacity() {
        int totalCapacity = 0;
        for (Unit unit : units) {
            totalCapacity += unit.getCapacity();
        }
        return totalCapacity;
    }

    public ArrayList<Unit> getUnits() {
        return this.units;
    }

    public void setUnits(ArrayList<Unit> units){ this.units = units; }

    public void addUnit(Unit unit) { units.add(unit); }

    /**
     * Remove unit from the building. If it is removed successfully it returns true,
     * otherwise it returns false.
     *
     * @param unit Unit should be removed from the building.
     * @return true if unit removed successfully, otherwise false.
     */
    public boolean removeUnit(Unit unit) {
        if (units.contains(unit)) {
            units.remove(unit);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        String s = getClass().getSimpleName() + " [id=" + getId() + ", capacity=" + getCapacity() + ", detail=" + getDetail() + "]"
                + ", units: ";
        for (Unit u : units){
            s += u.toString() + ", ";
        }
        return s;
    }
}
