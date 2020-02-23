package edu.luc.comp473.facilityMan.business.service.inventory;

import edu.luc.comp473.facilityMan.business.entities.facility.Facility;
import edu.luc.comp473.facilityMan.business.exceptions.DataAccessException;
import edu.luc.comp473.facilityMan.business.exceptions.DuplicatedEntityException;
import edu.luc.comp473.facilityMan.persistence.facility.FacilityDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Simple @see FacilityInventoryService implementation depending
 * on @see @FacilityDao.
 */
public class FacilityInventoryServiceImpl implements FacilityInventoryService {
    private final Logger logger = LoggerFactory.getLogger(FacilityInventoryServiceImpl.class);

    private FacilityDao dao;

    public FacilityInventoryServiceImpl(FacilityDao dao) {
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
    public void addNewFacility(Facility facility) {
        Facility facility2 = dao.findFacilityById(facility.getId());
        if (facility2 != null) {
            if (logger.isDebugEnabled()) {
                logger.debug("Tried to add facility which exists in our database");
            }
            throw new DuplicatedEntityException("The facility exists in our facilities already");

        }
        dao.saveFacility(facility);
    }

    @Override
    public boolean removeFacility(long id) {
        boolean result;
        try {
            Facility facility = dao.findFacilityById(id);
            if (facility == null) {
                if (logger.isDebugEnabled()) {
                    logger.debug("Tried to remove facility which does not exist:" + id);
                }
                result = false;
            } else {
                dao.removeFacility(id);
                if (logger.isDebugEnabled()) {
                    logger.debug("Facility removed:" + id);
                }
                result = true;
            }
        } catch (DataAccessException dae) {
            if (logger.isWarnEnabled()) {
                logger.warn("Failed to remove facility,", dae);
            }
            result = false;
        }
        return result;
    }

    @Override
    public Facility getFacility(long id) {
        return dao.findFacilityById(id);
    }
}
