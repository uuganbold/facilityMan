package edu.luc.comp473.facilityMan.business.facility.services;

import java.util.List;

import edu.luc.comp473.facilityMan.business.facility.dto.FacilityRepresentation;

public interface FacilityService {

    List<FacilityRepresentation> listAll();

}