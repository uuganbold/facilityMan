package edu.luc.comp473.facilityMan.business.service.facility.dto;

import edu.luc.comp473.facilityMan.business.entities.facility.FacilityDetail;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class FacilityDetailDTO {
    private Long id;
    private String address;
    private String description;

    private FacilityDTO facility;

    public static FacilityDetailDTO of(FacilityDetail b) {
        return (FacilityDetailDTO) new FacilityDetailDTO().setId(b.getId()).setAddress(b.getAddress())
                .setDescription(b.getDescription());
    }

    public FacilityDetail toEntity() {
        return (FacilityDetail) new FacilityDetail().setAddress(this.getAddress()).setDescription(this.getDescription())
                .setId(this.getId());
    }
}