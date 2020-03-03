package edu.luc.comp473.facilityMan.controllers.facility.payload;

import com.fasterxml.jackson.annotation.JsonInclude;

import edu.luc.comp473.facilityMan.business.service.facility.dto.UnitDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@Accessors(chain = true)
public class UnitRepresentation extends FacilityRepresentation {

    private BuildingRepresentation building;

    public static UnitRepresentation of(UnitDTO unit) {
        return (UnitRepresentation) new UnitRepresentation().fromFacility(unit);
    }
}