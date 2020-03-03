package edu.luc.comp473.facilityMan.business.entities.facility;

import java.util.ArrayList;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.experimental.Accessors;

/**
 * Building is a construction constructed by Units.
 */
@Entity
public class Building extends Facility {

    /**
     * A building is constructed by units.
     */
    @Getter
    @OneToMany(mappedBy = "building", orphanRemoval = true, cascade = CascadeType.ALL)
    private Set<Unit> units;

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

    public void addUnit(Unit unit) {
        units.add(unit);
        unit.setBuilding(this);
    }

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
            unit.setBuilding(null);
            return true;
        }
        return false;
    }
}
