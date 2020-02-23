package edu.luc.comp473.facilityMan.persistence.use;

import edu.luc.comp473.facilityMan.business.entities.use.FacilityUse;

import java.util.ArrayList;

/**
 * HashMap implementation of MaintenanceDao
 */
public class ArrayListUseDao implements UseDao {
    private final ArrayList<FacilityUse> uses;

    public ArrayListUseDao(){ this.uses = new ArrayList<>();}

    @Override
    public void addUse(FacilityUse use) {
        uses.add(use);
    }

    @Override
    public FacilityUse getUse(long id) {
        for (FacilityUse use: uses){
            if (use.getId() == id){
                return use;
            }
        }
        return null;
    }

    @Override
    public ArrayList<FacilityUse> getAllUses() {
        return uses;
    }
}
