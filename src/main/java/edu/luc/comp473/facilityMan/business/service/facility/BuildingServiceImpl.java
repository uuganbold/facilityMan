package edu.luc.comp473.facilityMan.business.service.facility;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import edu.luc.comp473.facilityMan.business.entities.facility.Building;
import edu.luc.comp473.facilityMan.business.exceptions.EntityNotFoundException;
import edu.luc.comp473.facilityMan.business.service.facility.dto.BuildingDTO;
import edu.luc.comp473.facilityMan.persistence.facility.BuildingRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BuildingServiceImpl implements BuildingService {

    private final BuildingRepository repository;

    @Override
    public BuildingDTO getById(long id) {
        Building b = repository.findById(id).orElse(null);
        if (b == null)
            return null;
        return BuildingDTO.of(b);
    }

    @Override
    public List<BuildingDTO> listAll() {
        return repository.findAll().stream().map(BuildingDTO::of).collect(Collectors.toList());
    }

    @Override
    public BuildingDTO create(BuildingDTO dto) {
        Building building = dto.toEntity();
        repository.save(building);
        dto.setId(building.getId());
        return dto;
    }

    @Override
    public void delete(long id) {
        // units should be deleted if hibernate mapping is correct.
        Building building = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Building not found with id:" + id));
        repository.delete(building);
    }

    @Override
    public void save(BuildingDTO b) {
        Building building = repository.findById(b.getId())
                .orElseThrow(() -> new EntityNotFoundException("Building not found with id:" + b.getId()));
        building.setName(b.getName());
        repository.save(building);
    }

}