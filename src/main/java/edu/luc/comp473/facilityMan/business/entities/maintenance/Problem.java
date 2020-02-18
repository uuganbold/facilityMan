package edu.luc.comp473.facilityMan.business.entities.maintenance;

public class Problem {
    private String description;

    public Problem(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
