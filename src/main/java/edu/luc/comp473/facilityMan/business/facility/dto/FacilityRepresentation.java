package edu.luc.comp473.facilityMan.business.facility.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import edu.luc.comp473.facilityMan.business.facility.entities.Facility;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@Accessors(chain = true)
public class FacilityRepresentation {

    private long id;

    private String name;

    private int capacity;

    private String type;

    protected FacilityRepresentation fromFacility(Facility facility) {
        this.setId(facility.getId()).setName(facility.getName()).setCapacity(facility.getCapacity())
                .setType(facility.getClass().getSimpleName());
        return this;
    }

    public static FacilityRepresentation of(Facility facility) {
        return new FacilityRepresentation().fromFacility(facility);
    }
}