package edu.luc.comp473.facilityMan.business.service;


import edu.luc.comp473.facilityMan.business.use.FacilityUse;
import edu.luc.comp473.facilityMan.business.util.Schedule;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface FacilityUseService {
    boolean isInUseDuringInterval(long id, Date start, Date end);
    FacilityUse assignFacilityToUse(long id, Schedule s);
    boolean vacateFacility(long id);
    List<FacilityUse> listActualUsage();
    BigDecimal calcUsageRate(FacilityUse use);
}
