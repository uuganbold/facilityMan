package edu.luc.comp473.facilityMan.business.facility.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import edu.luc.comp473.facilityMan.business.facility.dto.BuildingRepresentation;
import edu.luc.comp473.facilityMan.business.facility.dto.UnitRepresentation;
import edu.luc.comp473.facilityMan.business.facility.dto.UnitRequest;
import edu.luc.comp473.facilityMan.business.facility.entities.Building;
import edu.luc.comp473.facilityMan.business.facility.entities.Unit;
import edu.luc.comp473.facilityMan.business.exceptions.EntityNotFoundException;
import edu.luc.comp473.facilityMan.business.exceptions.InvalidUsageException;
import edu.luc.comp473.facilityMan.persistence.facility.BuildingRepository;
import edu.luc.comp473.facilityMan.persistence.facility.UnitRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UnitServiceImpl implements UnitService {

    private final UnitRepository repository;
    private final BuildingRepository buildingRepository;

    @Override
    public UnitRepresentation getById(long id) {
        Unit unit = repository.findById(id).orElse(null);
        if (unit == null)
            return null;
        return UnitRepresentation.of(unit).setBuilding(BuildingRepresentation.of(unit.getBuilding()));
    }

    @Override
    public List<UnitRepresentation> listAll() {
        return repository.findAll().stream().map(UnitRepresentation::of).collect(Collectors.toList());
    }

    @Override
    public UnitRepresentation create(UnitRequest dto) {
        Building building = buildingRepository.findById(dto.getBuilding()).orElseThrow(
                () -> new EntityNotFoundException("Building not found with id:" + dto.getBuilding()));
        Unit unit = dto.toEntity();
        building.addUnit(unit);
        repository.save(unit);
        return UnitRepresentation.of(unit).setBuilding(BuildingRepresentation.of(building));
    }

    @Override
    public void delete(long id) {
        Unit unit = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Unit not found with id:" + id));
        repository.delete(unit);
    }

    @Override
    public UnitRepresentation save(long id, UnitRequest dto) {
        Building building = buildingRepository.findById(dto.getBuilding()).orElseThrow(
                () -> new EntityNotFoundException("Building not found with id:" + dto.getBuilding()));
        Unit unit = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Unit not found with id:" + id));
        if (!building.equals(unit.getBuilding())) {
            throw new InvalidUsageException("Cannot change unit's building");
        }
        unit.setCapacity(dto.getCapacity()).setName(dto.getName());
        repository.save(unit);
        return UnitRepresentation.of(unit).setBuilding(BuildingRepresentation.of(building));
    }

}