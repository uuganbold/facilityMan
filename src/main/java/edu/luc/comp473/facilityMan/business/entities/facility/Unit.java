package edu.luc.comp473.facilityMan.business.entities.facility;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * One type of primitive facility.
 */
@Entity
@Getter
@Setter
@Accessors(chain = true)
public class Unit extends Facility {

    /**
     * Unit has its capacity.
     */
    private int capacity;

    @ManyToOne
    @JoinColumn(name = "building_id", nullable = false)
    private Building building;

}
