package edu.luc.comp473.facilityMan.controllers.facility.payload;

import edu.luc.comp473.facilityMan.business.service.facility.dto.BuildingDTO;
import edu.luc.comp473.facilityMan.business.service.facility.dto.UnitDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UnitRequest extends FacilityRequest {

    private int capacity;

    private long building;

    public UnitDTO toDTO() {
        return (UnitDTO) new UnitDTO().setBuilding((BuildingDTO) new BuildingDTO().setId(building))
                .setCapacity(capacity).setName(this.getName());
    }

}