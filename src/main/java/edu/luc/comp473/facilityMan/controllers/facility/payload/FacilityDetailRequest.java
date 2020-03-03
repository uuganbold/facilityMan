package edu.luc.comp473.facilityMan.controllers.facility.payload;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import edu.luc.comp473.facilityMan.business.service.facility.dto.FacilityDetailDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class FacilityDetailRequest {
    private String address;
    private String description;

    public FacilityDetailDTO toDTO() {
        return (FacilityDetailDTO) new FacilityDetailDTO().setAddress(address).setDescription(description);
    }
}