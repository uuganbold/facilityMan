package edu.luc.comp473.facilityMan.persistence.facility;

import javax.transaction.Transactional;

import edu.luc.comp473.facilityMan.business.entities.facility.Facility;

@Transactional
public interface FacilityRepository extends FacilityBaseRepository<Facility> {

}