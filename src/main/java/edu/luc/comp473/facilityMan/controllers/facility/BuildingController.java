package edu.luc.comp473.facilityMan.controllers.facility;

import org.springframework.web.bind.annotation.RestController;

import edu.luc.comp473.facilityMan.business.exceptions.EntityNotFoundException;
import edu.luc.comp473.facilityMan.business.service.facility.BuildingService;
import edu.luc.comp473.facilityMan.business.service.facility.dto.BuildingDTO;
import edu.luc.comp473.facilityMan.controllers.facility.payload.BuildingRepresentation;
import edu.luc.comp473.facilityMan.controllers.facility.payload.BuildingRequest;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequiredArgsConstructor
@RequestMapping("/buildings")
public class BuildingController {

    private final BuildingService service;

    @GetMapping(value = "/{id}", produces = "application/json")
    public BuildingRepresentation getBuilding(@PathVariable long id) {
        BuildingDTO building = service.getById(id);
        if (building == null)
            throw new EntityNotFoundException("Building not found with id:" + id);
        return BuildingRepresentation.of(building);
    }

    @GetMapping(produces = "application/json")
    public List<BuildingRepresentation> getAllBuildings() {
        return service.listAll().stream().map(BuildingRepresentation::of).collect(Collectors.toList());
    }

    @PostMapping(value = "", consumes = "application/json", produces = "application/json")
    public BuildingRepresentation newBuilding(@RequestBody @Validated BuildingRequest buildingRequest) {
        BuildingDTO build = service.create(buildingRequest.toDTO());
        return BuildingRepresentation.of(build);
    }

    @PutMapping(value = "/{id}")
    public BuildingRepresentation updateBuilding(@PathVariable long id,
            @RequestBody @Validated BuildingRequest request) {
        BuildingDTO b = (BuildingDTO) request.toDTO().setId(id);
        service.save(b);
        return BuildingRepresentation.of(b);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable long id) {
        service.delete(id);
    }

}