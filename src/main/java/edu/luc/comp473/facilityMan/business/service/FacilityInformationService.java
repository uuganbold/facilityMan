package edu.luc.comp473.facilityMan.business.service;


import edu.luc.comp473.facilityMan.business.facility.FacilityDetail;

public interface FacilityInformationService {
    FacilityDetail getFacilityInformation(Long id);
    void addFacilityDetail(Long id, FacilityDetail detail);
    int requestAvailableCapacity(Long id);
}
