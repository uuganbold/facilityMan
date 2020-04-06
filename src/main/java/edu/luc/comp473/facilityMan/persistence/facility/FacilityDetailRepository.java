package edu.luc.comp473.facilityMan.persistence.facility;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.luc.comp473.facilityMan.business.facility.entities.FacilityDetail;

public interface FacilityDetailRepository extends JpaRepository<FacilityDetail, Long> {

}