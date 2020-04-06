package edu.luc.comp473.facilityMan.business.facility.services;

import java.util.List;

import edu.luc.comp473.facilityMan.business.facility.dto.BuildingRepresentation;
import edu.luc.comp473.facilityMan.business.facility.dto.BuildingRequest;

public interface BuildingService {

    BuildingRepresentation getById(long id);

    List<BuildingRepresentation> listAll();

    BuildingRepresentation create(BuildingRequest dto);

    BuildingRepresentation save(long id, BuildingRequest b);

    void delete(long id);

}