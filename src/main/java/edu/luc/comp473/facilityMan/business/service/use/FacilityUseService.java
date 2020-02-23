package edu.luc.comp473.facilityMan.business.service.use;

import edu.luc.comp473.facilityMan.business.entities.use.FacilityUse;
import edu.luc.comp473.facilityMan.business.entities.util.Schedule;

import java.util.Date;
import java.util.List;

/**
 * Interface defining behaviours regarding FacilitityUse.
 */
public interface FacilityUseService {
    boolean isInUseDuringInterval(long id, Date start, Date end);

    void assignFacilityToUse(long id, Schedule s);

    void vacateFacility(long id);

    List<FacilityUse> listActualUsage();

    long calcUsageRate(FacilityUse use);
}
