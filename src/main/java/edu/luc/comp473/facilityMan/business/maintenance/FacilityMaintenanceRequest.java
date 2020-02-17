package edu.luc.comp473.facilityMan.business.maintenance;

import java.util.Date;

public class FacilityMaintenanceRequest {
    private Date dateSubmitted;
    private String description;

    public FacilityMaintenanceRequest(String description) {
        this.dateSubmitted = new Date();
        this.description = description;
    }

    public Date getDateSubmitted() {
        return dateSubmitted;
    }

    public String getDescription() {
        return description;
    }
}
