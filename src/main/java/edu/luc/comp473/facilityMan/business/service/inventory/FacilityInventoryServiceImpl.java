package edu.luc.comp473.facilityMan.business.service.inventory;

import edu.luc.comp473.facilityMan.business.entities.facility.Facility;
import edu.luc.comp473.facilityMan.business.entities.facility.FacilityDetail;
import edu.luc.comp473.facilityMan.business.exceptions.DuplicatedEntityException;
import edu.luc.comp473.facilityMan.persistence.inventory.FacilityInventoryDao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Simple @see FacilityInventoryService implementation depending
 * on @see @FacilityInventoryDao.
 */
public class FacilityInventoryServiceImpl implements FacilityInventoryService {
    private final Logger logger = LoggerFactory.getLogger(FacilityInventoryServiceImpl.class);

    private FacilityInventoryDao dao;

    public FacilityInventoryServiceImpl(FacilityInventoryDao dao) {
        this.dao = dao;
    }

    @Override
    public List<Facility> listFacilities() {
        if (logger.isDebugEnabled()) {
            logger.debug("Reading all facilities from DAO");
        }
        return dao.findAllFacilities();
    }

    @Override
    public Facility getFacility(long id) {
        if (logger.isDebugEnabled()) {
            logger.debug("Reading facility with id:" + id);
        }
        return dao.findFacilityById(id);
    }

    @Override
    public void addNewFacility(Facility facility) {
        Facility facility2 = dao.findFacilityById(facility.getId());
        if (facility2 != null) {
            throw new DuplicatedEntityException("The facility is exists in our facilities already");

        }
        dao.saveFacility(facility);
    }

    @Override
    public boolean removeFacility(Long id) {
        return false;
    }

    @Override
    public void addFacilityDetail(Long id, FacilityDetail detail) {
        // TODO Auto-generated method stub

    }

    @Override
    public FacilityDetail getFacilityInformation(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int requestAvailableCapacity(Long id) {
        // TODO Auto-generated method stub
        return 0;
    }

}
