package edu.luc.comp473.facilityMan.controllers.facility.payload;

import com.fasterxml.jackson.annotation.JsonInclude;

import edu.luc.comp473.facilityMan.business.service.facility.dto.BuildingDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@Accessors(chain = true)
public class BuildingRepresentation extends FacilityRepresentation {

    private int unitNumber;

    public static BuildingRepresentation of(BuildingDTO building) {
        return (BuildingRepresentation) (new BuildingRepresentation().setUnitNumber(building.getUnitNumber())
                .fromFacility(building));
    }

}