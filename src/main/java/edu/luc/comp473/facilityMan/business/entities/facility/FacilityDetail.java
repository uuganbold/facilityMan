package edu.luc.comp473.facilityMan.business.entities.facility;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

import edu.luc.comp473.facilityMan.business.entities.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * Detailed information about Facility.
 */
@Entity
@Getter
@Setter
@Accessors(chain = true)
public class FacilityDetail extends BaseEntity {

    @OneToOne(mappedBy = "detail")
    private Facility facility;

    /**
     * Facilities have a description.
     */
    private String description;

    private String address;

}
