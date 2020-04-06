package edu.luc.comp473.facilityMan.business.facility.entities;

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
     *
     */
    private static final long serialVersionUID = 7622965004154267624L;

    /**
     * Unit has its capacity.
     */
    private int capacity;

    @ManyToOne
    @JoinColumn(name = "building_id", nullable = false)
    private Building building;

}
