package edu.luc.comp473.facilityMan.business.service.facility;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import edu.luc.comp473.facilityMan.business.entities.facility.Building;
import edu.luc.comp473.facilityMan.business.entities.facility.Unit;
import edu.luc.comp473.facilityMan.business.exceptions.EntityNotFoundException;
import edu.luc.comp473.facilityMan.business.exceptions.InvalidUsageException;
import edu.luc.comp473.facilityMan.business.service.facility.dto.BuildingDTO;
import edu.luc.comp473.facilityMan.business.service.facility.dto.UnitDTO;
import edu.luc.comp473.facilityMan.persistence.facility.BuildingRepository;
import edu.luc.comp473.facilityMan.persistence.facility.UnitRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UnitServiceImpl implements UnitService {

    private final UnitRepository repository;
    private final BuildingRepository buildingRepository;

    @Override
    public UnitDTO getById(long id) {
        Unit unit = repository.findById(id).orElse(null);
        if (unit == null)
            return null;
        return UnitDTO.of(unit).setBuilding(BuildingDTO.of(unit.getBuilding()));
    }

    @Override
    public List<UnitDTO> listAll() {
        return repository.findAll().stream().map(UnitDTO::of).collect(Collectors.toList());
    }

    @Override
    public UnitDTO create(UnitDTO dto) {
        Building building = buildingRepository.findById(dto.getBuilding().getId()).orElseThrow(
                () -> new EntityNotFoundException("Building not found with id:" + dto.getBuilding().getId()));
        Unit unit = dto.toEntity();
        building.addUnit(unit);
        repository.save(unit);
        dto.setBuilding(BuildingDTO.of(building)).setId(unit.getId());
        return dto;
    }

    @Override
    public void delete(long id) {
        Unit unit = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Unit not found with id:" + id));
        repository.delete(unit);
    }

    @Override
    public void save(UnitDTO dto) {
        Building building = buildingRepository.findById(dto.getBuilding().getId()).orElseThrow(
                () -> new EntityNotFoundException("Building not found with id:" + dto.getBuilding().getId()));
        Unit unit = repository.findById(dto.getId())
                .orElseThrow(() -> new EntityNotFoundException("Unit not found with id:" + dto.getId()));
        if (!building.equals(unit.getBuilding())) {
            throw new InvalidUsageException("Cannot change unit's building");
        }
        unit.setCapacity(dto.getCapacity()).setName(dto.getName());
        dto.setBuilding(BuildingDTO.of(building));
        repository.save(unit);
    }

}