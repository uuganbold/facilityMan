package edu.luc.comp473.facilityMan.controllers.facility;

import org.springframework.web.bind.annotation.RestController;

import edu.luc.comp473.facilityMan.business.exceptions.EntityNotFoundException;
import edu.luc.comp473.facilityMan.business.facility.dto.FacilityDetailRepresentation;
import edu.luc.comp473.facilityMan.business.facility.dto.FacilityDetailRequest;
import edu.luc.comp473.facilityMan.business.facility.dto.FacilityRepresentation;
import edu.luc.comp473.facilityMan.business.facility.services.FacilityDetailService;
import edu.luc.comp473.facilityMan.business.facility.services.FacilityService;
import lombok.RequiredArgsConstructor;

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

@RestController
@RequiredArgsConstructor
@RequestMapping("/facilities")
public class FacilityController {

    private final FacilityService service;
    private final FacilityDetailService detailService;

    @GetMapping(value = "")
    public List<FacilityRepresentation> getFacilities() {
        return service.listAll();
    }

    @GetMapping(value = "/{id}/details")
    public FacilityDetailRepresentation getDetails(@PathVariable long id) {
        FacilityDetailRepresentation dto = detailService.getById(id);
        if (dto == null)
            throw new EntityNotFoundException("Facility details not found with id:" + id);
        return dto;
    }

    @PostMapping(value = "/{id}/details")
    public FacilityDetailRepresentation newDetails(@PathVariable long id,
            @RequestBody @Validated FacilityDetailRequest request) {
        return updateDetails(id, request);
    }

    @PutMapping(value = "/{id}/details")
    public FacilityDetailRepresentation updateDetails(@PathVariable long id,
            @RequestBody @Validated FacilityDetailRequest request) {
        return detailService.create(id,request);
    }

    @DeleteMapping(value = "/{id}/details")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDetails(@PathVariable long id) {
        detailService.delete(id);
    }

}