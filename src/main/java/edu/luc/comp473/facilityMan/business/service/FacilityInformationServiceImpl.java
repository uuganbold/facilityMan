package edu.luc.comp473.facilityMan.business.service;

import edu.luc.comp473.facilityMan.business.facility.Facility;
import edu.luc.comp473.facilityMan.business.facility.FacilityDetail;

public class FacilityInformationServiceImpl implements FacilityInformationService {

    @Override
    public FacilityDetail getFacilityInformation(Long id) {
        Facility f = FacilityInventoryServiceImpl.getInstance().getFacility(id);
        if (f != null) return f.getDetail();
        else return null;
    }

    @Override
    public void addFacilityDetail(Long id, FacilityDetail detail) {
        Facility f = FacilityInventoryServiceImpl.getInstance().getFacility(id);
        if (f != null) f.setDetail(detail);
    }

    @Override
    public int requestAvailableCapacity(Long id) {
        Facility f = FacilityInventoryServiceImpl.getInstance().getFacility(id);
        if (f != null) return f.getCapacity();
        else return 0;
    }
}
