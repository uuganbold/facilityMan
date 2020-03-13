package edu.luc.comp473.facilityMan.persistence.use;

import edu.luc.comp473.facilityMan.business.entities.use.FacilityUse;

import java.util.ArrayList;
import java.util.List;

/**
 * HashMap implementation of MaintenanceDao
 */
public class ArrayListUseDao implements UseDao {
    private final List<FacilityUse> uses;

    public ArrayListUseDao(List<FacilityUse> uses){ this.uses = uses;}

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
    public List<FacilityUse> getAllUses() {
        return uses;
    }
}
