package edu.luc.comp473.facilityMan.controllers.facility.payload;

import com.fasterxml.jackson.annotation.JsonInclude;

import edu.luc.comp473.facilityMan.business.service.facility.dto.FacilityDetailDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@Accessors(chain = true)
public class FacilityDetailRepresentation {
    private long id;
    private String address;
    private String description;
    private FacilityRepresentation facility;

    public static FacilityDetailRepresentation of(FacilityDetailDTO details) {
        return (FacilityDetailRepresentation) (new FacilityDetailRepresentation().setAddress(details.getAddress())
                .setDescription(details.getDescription())).setId(details.getId());
    }
}