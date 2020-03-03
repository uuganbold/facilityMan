package edu.luc.comp473.facilityMan.business.service.facility;

import java.util.List;

import edu.luc.comp473.facilityMan.business.service.facility.dto.BuildingDTO;

public interface BuildingService {

    BuildingDTO getById(long id);

    List<BuildingDTO> listAll();

    BuildingDTO create(BuildingDTO dto);

    void save(BuildingDTO b);

    void delete(long id);

}