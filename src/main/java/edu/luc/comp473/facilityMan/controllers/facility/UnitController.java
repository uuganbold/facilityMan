package edu.luc.comp473.facilityMan.controllers.facility;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import edu.luc.comp473.facilityMan.business.exceptions.EntityNotFoundException;
import edu.luc.comp473.facilityMan.business.service.facility.UnitService;
import edu.luc.comp473.facilityMan.business.service.facility.dto.UnitDTO;
import edu.luc.comp473.facilityMan.controllers.facility.payload.BuildingRepresentation;
import edu.luc.comp473.facilityMan.controllers.facility.payload.UnitRepresentation;
import edu.luc.comp473.facilityMan.controllers.facility.payload.UnitRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/units")
public class UnitController {

    private final UnitService service;

    @GetMapping(value = "/{id}", produces = "application/json")
    public UnitRepresentation getUnit(@PathVariable long id) {
        UnitDTO unit = service.getById(id);
        if (unit == null)
            throw new EntityNotFoundException("Building not found with id:" + id);
        return UnitRepresentation.of(unit).setBuilding(BuildingRepresentation.of(unit.getBuilding()));
    }

    @GetMapping(produces = "application/json")
    public List<UnitRepresentation> getAllUnits() {
        return service.listAll().stream().map(UnitRepresentation::of).collect(Collectors.toList());
    }

    @PostMapping(value = "", consumes = "application/json", produces = "application/json")
    public UnitRepresentation newBuilding(@RequestBody @Validated UnitRequest request) {
        UnitDTO unit = service.create(request.toDTO());
        return UnitRepresentation.of(unit).setBuilding(BuildingRepresentation.of(unit.getBuilding()));
    }

    @PutMapping(value = "/{id}")
    public UnitRepresentation updateBuilding(@PathVariable long id, @RequestBody @Validated UnitRequest request) {
        UnitDTO b = (UnitDTO) request.toDTO().setId(id);
        service.save(b);
        return UnitRepresentation.of(b).setBuilding(BuildingRepresentation.of(b.getBuilding()));
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable long id) {
        service.delete(id);
    }
}