package edu.luc.comp473.facilityMan.business.facility.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import edu.luc.comp473.facilityMan.business.facility.dto.BuildingRepresentation;
import edu.luc.comp473.facilityMan.business.facility.dto.BuildingRequest;
import edu.luc.comp473.facilityMan.business.facility.entities.Building;
import edu.luc.comp473.facilityMan.business.exceptions.EntityNotFoundException;
import edu.luc.comp473.facilityMan.persistence.facility.BuildingRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BuildingServiceImpl implements BuildingService {

    private final BuildingRepository repository;

    @Override
    public BuildingRepresentation getById(long id) {
        Building b = repository.findById(id).orElse(null);
        if (b == null)
            return null;
        return BuildingRepresentation.of(b);
    }

    @Override
    public List<BuildingRepresentation> listAll() {
        return repository.findAll().stream().map(BuildingRepresentation::of).collect(Collectors.toList());
    }

    @Override
    public BuildingRepresentation create(BuildingRequest dto) {
        Building building = dto.toEntity();
        repository.save(building);
        return BuildingRepresentation.of(building);
    }

    @Override
    public void delete(long id) {
        // units should be deleted if hibernate mapping is correct.
        Building building = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Building not found with id:" + id));
        repository.delete(building);
    }

    @Override
    public BuildingRepresentation save(long id,BuildingRequest b) {
        Building building = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Building not found with id:" + id));
        building.setName(b.getName());
        repository.save(building);
        return BuildingRepresentation.of(building);
    }

}