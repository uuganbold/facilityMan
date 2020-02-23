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

    public Schedule() {
    }

    public long getNumberOfDays() {
        return TimeUnit.DAYS.convert(endDate.getTime() - startDate.getTime(), TimeUnit.MILLISECONDS);
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "Schedule [endDate=" + endDate + ", startDate=" + startDate + "]";
    }

}
