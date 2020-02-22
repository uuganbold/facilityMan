package edu.luc.comp473.facilityMan.business.service.information;

import edu.luc.comp473.facilityMan.business.entities.facility.Facility;
import edu.luc.comp473.facilityMan.business.entities.facility.FacilityDetail;
import edu.luc.comp473.facilityMan.business.exceptions.DataAccessException;
import edu.luc.comp473.facilityMan.persistence.facility.FacilityDao;

/**
 * Simple @see FacilityInformationService implementation depending on DAO.
 */
public class FacilityInformationServiceImpl implements FacilityInformationService {

    private FacilityDao dao;

    public FacilityInformationServiceImpl(FacilityDao dao) {
        this.dao = dao;
    }

    @Override
    public void addFacilityDetail(Long id, FacilityDetail detail) {
        Facility facility = dao.findFacilityById(id);
        if (facility != null){
            facility.setDetail(detail);
        } else{
            throw new DataAccessException("Tried adding detail to a facility which doesn't exist.");
        }
    }

    @Override
    public FacilityDetail getFacilityInformation(Long id) {
        Facility facility = dao.findFacilityById(id);
        if (facility != null){
            return facility.getDetail();
        } else{
            throw new DataAccessException("Tried retrieving information from facility which doesn't exist.");
        }
    }

    @Override
    public int requestAvailableCapacity(Long id) {
        Facility facility = dao.findFacilityById(id);
        if(facility != null){
            return facility.getCapacity();
        }
        throw new DataAccessException("Tried retrieving capacity from facility which doesn't exist.");
    }
}
