package edu.luc.comp473.facilityMan.business.facility.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import edu.luc.comp473.facilityMan.business.facility.entities.Unit;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@Accessors(chain = true)
public class UnitRepresentation extends FacilityRepresentation {

    private BuildingRepresentation building;

    public static UnitRepresentation of(Unit unit) {
        return (UnitRepresentation) new UnitRepresentation().fromFacility(unit);
    }
}