package edu.luc.comp473.facilityMan.business.facility.entities;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

import edu.luc.comp473.facilityMan.business.base.BaseEntity;
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

    /**
     *
     */
    private static final long serialVersionUID = 1719953822812180582L;

    @OneToOne(mappedBy = "detail")
    private Facility facility;

    /**
     * Facilities have a description.
     */
    private String description;

    private String address;

}
