package edu.luc.comp473.facilityMan.business.service.facility;

import java.util.List;

import edu.luc.comp473.facilityMan.business.service.facility.dto.UnitDTO;

public interface UnitService {
    UnitDTO getById(long id);

    List<UnitDTO> listAll();

    UnitDTO create(UnitDTO unit);

    void save(UnitDTO b);

    void delete(long id);
}