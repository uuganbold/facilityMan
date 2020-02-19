package edu.luc.comp473.facilityMan.business.entities.util;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Schedule. It holds startDate, endDate for any activities scheduled in the
 * system.
 */
public class Schedule {

    private Date startDate;
    private Date endDate;

    public Schedule(Date startDate, Date endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public long getNumberOfDays() {
        return TimeUnit.DAYS.convert(endDate.getTime() - startDate.getTime(), TimeUnit.MILLISECONDS);
    }
}
