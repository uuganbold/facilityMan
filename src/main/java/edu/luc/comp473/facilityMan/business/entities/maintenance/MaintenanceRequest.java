package edu.luc.comp473.facilityMan.business.entities.maintenance;

import java.util.Date;

/**
 * Entity for facility maintenance request.
 */
public class MaintenanceRequest {
    private Date dateSubmitted;
    private String description;
    private long id;

    public MaintenanceRequest(Long id, String description) {
        this.dateSubmitted = new Date();
        this.description = description;
        this.id = id;
    }

    public Date getDateSubmitted() {
        return dateSubmitted;
    }

    public String getDescription() {
        return description;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
}
