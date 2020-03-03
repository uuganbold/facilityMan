package edu.luc.comp473.facilityMan.persistence.facility;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import edu.luc.comp473.facilityMan.business.entities.facility.Facility;

@NoRepositoryBean
public interface FacilityBaseRepository<T extends Facility> extends JpaRepository<T, Long> {

}