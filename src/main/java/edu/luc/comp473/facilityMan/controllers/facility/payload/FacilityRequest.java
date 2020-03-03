package edu.luc.comp473.facilityMan.controllers.facility.payload;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class FacilityRequest {

    @NotNull
    @Size(min = 3, max = 15)
    private String name;
}