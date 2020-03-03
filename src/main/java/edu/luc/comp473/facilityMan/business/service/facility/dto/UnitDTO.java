package edu.luc.comp473.facilityMan.business.service.facility.dto;

import edu.luc.comp473.facilityMan.business.entities.facility.Unit;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class UnitDTO extends FacilityDTO {

    private BuildingDTO building;

    public static UnitDTO of(Unit unit) {
        return (UnitDTO) new UnitDTO().setCapacity(unit.getCapacity()).setName(unit.getName()).setId(unit.getId());
    }

    public Unit toEntity() {
        Unit unit = new Unit();
        return (Unit) unit.setCapacity(this.getCapacity()).setName(getName()).setId(getId());
    }
}