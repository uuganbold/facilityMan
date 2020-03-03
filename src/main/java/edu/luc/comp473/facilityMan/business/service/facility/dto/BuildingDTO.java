package edu.luc.comp473.facilityMan.business.service.facility.dto;

import edu.luc.comp473.facilityMan.business.entities.facility.Building;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class BuildingDTO extends FacilityDTO {

    private int unitNumber;

    public static BuildingDTO of(Building b) {
        return (BuildingDTO) new BuildingDTO().setUnitNumber(b.getUnits().size()).setCapacity(b.getCapacity())
                .setName(b.getName()).setId(b.getId());
    }

    public Building toEntity() {
        return (Building) new Building().setName(this.getName()).setId(this.getId());
    }
}