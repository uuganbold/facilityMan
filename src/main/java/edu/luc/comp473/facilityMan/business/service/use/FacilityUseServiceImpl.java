package edu.luc.comp473.facilityMan.business.service.use;

import edu.luc.comp473.facilityMan.business.entities.use.FacilityUse;
import edu.luc.comp473.facilityMan.business.entities.util.Schedule;
import edu.luc.comp473.facilityMan.persistence.use.UseDao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Simple @see FacilityUseService implementation depending on DAO.
 */
public class FacilityUseServiceImpl implements FacilityUseService {
    private UseDao useDao;

    public FacilityUseServiceImpl(UseDao useDao){
        this.useDao = useDao;
    }

    @Override
    public boolean isInUseDuringInterval(long id, Date start, Date end) {
        List<FacilityUse> uses = useDao.getAllUses();

        for (FacilityUse use: uses){
            if (use.getId() == id) {
                if (use.getSchedule().getStartDate().after(start) &&
                        use.getSchedule().getEndDate().before(end))
                    return true;
            }
        }

        return false;
    }

    @Override
    public void assignFacilityToUse(long id, Schedule s) {
        FacilityUse use = new FacilityUse();
        use.setFacility(id);
        use.setSchedule(s);
        useDao.addUse(use);
    }

    @Override
    public void vacateFacility(long id) {
        List<FacilityUse> uses = useDao.getAllUses();
        uses.removeIf(use -> use.getId() == id);
    }

    @Override
    public List<FacilityUse> listActualUsage() {
        return useDao.getAllUses();
    }

    @Override
    public long calcUsageRate(FacilityUse use) {
        Schedule schedule = use.getSchedule();
        long length = schedule.getNumberOfDays();
        return length/365;
    }
}
