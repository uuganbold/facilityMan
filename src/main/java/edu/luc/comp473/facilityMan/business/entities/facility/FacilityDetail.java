package edu.luc.comp473.facilityMan.business.entities.facility;

/**
 * Detailed information about Facility.
 */
public class FacilityDetail {

    /**
     * Facilities have a name.
     */
    private String name;

    /**
     * Facilities have a description.
     */
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public FacilityDetail(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public FacilityDetail() {
    }

    @Override
    public String toString() {
        return "FacilityDetail [name=" + name + ", description=" + description + "]";
    }
}
