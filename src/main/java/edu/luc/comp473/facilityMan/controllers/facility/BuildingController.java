package edu.luc.comp473.facilityMan.controllers.facility;

import org.springframework.web.bind.annotation.RestController;

import edu.luc.comp473.facilityMan.business.exceptions.EntityNotFoundException;
import edu.luc.comp473.facilityMan.business.facility.services.BuildingService;
import edu.luc.comp473.facilityMan.business.facility.dto.BuildingRepresentation;
import edu.luc.comp473.facilityMan.business.facility.dto.BuildingRequest;
import lombok.RequiredArgsConstructor;

import java.util.List;

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
        BuildingRepresentation building = service.getById(id);
        if (building == null)
            throw new EntityNotFoundException("Building not found with id:" + id);
        return building;
    }

    @GetMapping(produces = "application/json")
    public List<BuildingRepresentation> getAllBuildings() {
        return service.listAll();
    }

    @PostMapping(value = "", consumes = "application/json", produces = "application/json")
    public BuildingRepresentation newBuilding(@RequestBody @Validated BuildingRequest buildingRequest) {
        return service.create(buildingRequest);
    }

    @PutMapping(value = "/{id}")
    public BuildingRepresentation updateBuilding(@PathVariable long id,
            @RequestBody @Validated BuildingRequest request) {
        return service.save(id,request);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable long id) {
        service.delete(id);
    }

}