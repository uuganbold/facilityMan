package edu.luc.comp473.facilityMan.business.service.facility.dto;

import edu.luc.comp473.facilityMan.business.entities.facility.Facility;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class FacilityDTO {
    private Long id;
    private String name;
    private int capacity;
    private FacilityDetailDTO details;

    private String type;

    public static FacilityDTO of(Facility facility) {
        return (FacilityDTO) new FacilityDTO().setCapacity(facility.getCapacity()).setName(facility.getName())
                .setId(facility.getId()).setType(facility.getClass().getSimpleName());
    }

}