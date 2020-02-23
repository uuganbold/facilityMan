package edu.luc.comp473.facilityMan.persistence.use;

import edu.luc.comp473.facilityMan.business.entities.use.FacilityUse;

import java.util.List;

public interface UseDao {
    void addUse(FacilityUse use);

    FacilityUse getUse(long id);

    List<FacilityUse> getAllUses();
}
