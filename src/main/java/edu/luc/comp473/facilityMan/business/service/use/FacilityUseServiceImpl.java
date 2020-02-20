package edu.luc.comp473.facilityMan.business.service.use;

import edu.luc.comp473.facilityMan.business.entities.use.FacilityUse;
import edu.luc.comp473.facilityMan.business.entities.util.Schedule;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Simple @see FacilityUseService implementation depending on DAO.
 */
public class FacilityUseServiceImpl implements FacilityUseService {

    private List<FacilityUse> uses = new ArrayList<>();

    @Override
    // TODO: Complete this implementation
    public boolean isInUseDuringInterval(long id, Date start, Date end) {

        return false;

    }

    @Override
    public FacilityUse assignFacilityToUse(long id, Schedule s) {
        /*
         * Facility f = FacilityInventoryServiceImpl.getInstance().getFacility(id); if
         * (f != null) { FacilityUse use = new FacilityUse(f, s); uses.add(use); return
         * use; }
         */

        return null;
    }

    @Override
    public boolean vacateFacility(long id) {
        /*
         * Facility f = FacilityInventoryServiceImpl.getInstance().getFacility(id); if
         * (f != null) { for (FacilityUse use : uses) { if (use.getFacility().getId() ==
         * id) { uses.remove(use); return true; } } }
         */
        return false;
    }

    @Override
    public List<FacilityUse> listActualUsage() {
        return uses;
    }

    @Override
    // TODO: Complete this implementation
    public BigDecimal calcUsageRate(FacilityUse use) {
        return null;
    }
}
