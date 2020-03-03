package edu.luc.comp473.facilityMan.controllers.facility.payload;

import com.fasterxml.jackson.annotation.JsonInclude;

import edu.luc.comp473.facilityMan.business.service.facility.dto.FacilityDTO;
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

    protected FacilityRepresentation fromFacility(FacilityDTO facility) {
        this.setId(facility.getId()).setName(facility.getName()).setCapacity(facility.getCapacity())
                .setType(facility.getType());
        return this;
    }

    public static FacilityRepresentation of(FacilityDTO facility) {
        return new FacilityRepresentation().fromFacility(facility);
    }
}