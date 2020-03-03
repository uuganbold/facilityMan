package edu.luc.comp473.facilityMan.business.service.facility;

import java.util.List;

import edu.luc.comp473.facilityMan.business.service.facility.dto.FacilityDTO;

public interface FacilityService {

    List<FacilityDTO> listAll();

}