package edu.luc.comp473.facilityMan.business.facility.dto;

import edu.luc.comp473.facilityMan.business.facility.entities.Building;
import edu.luc.comp473.facilityMan.business.facility.entities.Unit;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UnitRequest extends FacilityRequest {

    private int capacity;

    private long building;

    public Unit toEntity() {
        return (Unit) new Unit().setBuilding((Building) new Building().setId(building))
                .setCapacity(capacity).setName(this.getName());
    }

}