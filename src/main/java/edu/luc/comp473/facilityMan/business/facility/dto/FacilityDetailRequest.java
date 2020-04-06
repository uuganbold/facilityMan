package edu.luc.comp473.facilityMan.business.facility.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import edu.luc.comp473.facilityMan.business.facility.entities.FacilityDetail;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class FacilityDetailRequest {
    private String address;
    private String description;

    public FacilityDetail toEntity() {
        return  (new FacilityDetail()).setAddress(address).setDescription(description);
    }
}