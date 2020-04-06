package edu.luc.comp473.facilityMan.business.facility.services;

import java.util.List;

import edu.luc.comp473.facilityMan.business.facility.dto.UnitRepresentation;
import edu.luc.comp473.facilityMan.business.facility.dto.UnitRequest;

public interface UnitService {
    UnitRepresentation getById(long id);

    List<UnitRepresentation> listAll();

    UnitRepresentation create(UnitRequest unit);

    UnitRepresentation save(long id, UnitRequest b);

    void delete(long id);
}