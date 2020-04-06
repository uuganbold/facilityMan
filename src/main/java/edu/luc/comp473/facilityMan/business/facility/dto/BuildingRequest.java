package edu.luc.comp473.facilityMan.business.facility.dto;

import edu.luc.comp473.facilityMan.business.facility.entities.Building;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BuildingRequest extends FacilityRequest {

    public Building toEntity() {
        return (Building) new Building().setName(this.getName());
    }
}