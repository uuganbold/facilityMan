package edu.luc.comp473.facilityMan.business.entities.facility;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import edu.luc.comp473.facilityMan.business.entities.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * Facility is the main object the system manages. All Facilities should extend
 * this class.
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Accessors(chain = true)
public abstract class Facility extends BaseEntity {

    @Getter
    @Setter
    private String name;

    /**
     * Each facility should have details about that facility.
     */

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @PrimaryKeyJoinColumn
    @Getter
    private FacilityDetail detail;

    /**
     * Each facility should have capacity.
     *
     * @return capacity of the facility
     */
    public abstract int getCapacity();

    public void addDetails(FacilityDetail detail) {
        this.detail = detail;
        detail.setFacility(this);
    }

    public void removeDetails(FacilityDetail details) {
        if (details != null) {
            details.setFacility(null);
        }
        this.detail = null;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " [id=" + getId() + ", capacity=" + getCapacity() + ", detail=" + detail
                + "]";
    }

}
