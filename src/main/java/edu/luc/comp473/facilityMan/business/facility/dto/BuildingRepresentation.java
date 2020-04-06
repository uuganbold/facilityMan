package edu.luc.comp473.facilityMan.business.facility.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import edu.luc.comp473.facilityMan.business.facility.entities.Building;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@Accessors(chain = true)
public class BuildingRepresentation extends FacilityRepresentation {

    private int unitNumber;

    public static BuildingRepresentation of(Building building) {
        return (BuildingRepresentation) (new BuildingRepresentation().setUnitNumber(building.getUnits().size())
                .fromFacility(building));
    }

}