package edu.luc.comp473.facilityMan.controllers.facility.payload;

import edu.luc.comp473.facilityMan.business.service.facility.dto.BuildingDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BuildingRequest extends FacilityRequest {

    public BuildingDTO toDTO() {
        return (BuildingDTO) new BuildingDTO().setName(this.getName());
    }
}