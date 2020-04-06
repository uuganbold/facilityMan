package edu.luc.comp473.facilityMan.persistence.facility;

import javax.transaction.Transactional;

import edu.luc.comp473.facilityMan.business.facility.entities.Building;

@Transactional
public interface BuildingRepository extends FacilityBaseRepository<Building> {

}