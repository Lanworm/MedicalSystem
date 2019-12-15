package com.tsystems.javaschool.medical.backend.controller;

import com.tsystems.javaschool.medical.backend.dto.SpecializationsDto;
import com.tsystems.javaschool.medical.backend.services.SpecializationsService;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping("/api")
public class SpecializationsController {

    private final SpecializationsService specializationsService;

    public SpecializationsController(SpecializationsService specializationsService) {
        this.specializationsService = specializationsService;
    }


    @RequestMapping(value = "/specializations", method = RequestMethod.GET)
    public List<SpecializationsDto> getSpecializations() {
        return specializationsService.getSpecializationsList();
    }

    @RequestMapping(value = "/specializations", method = RequestMethod.PUT)
    public List<SpecializationsDto> addSpecialization(
            @RequestParam(value = "description") String description
    ) {
        specializationsService.addSpecialization(description);
        return specializationsService.getSpecializationsList();
    }

    @RequestMapping(value = "/specializations/{id}", method = RequestMethod.DELETE)
    public List<SpecializationsDto> deleteSpecialization(@PathVariable("id") final BigInteger specializationId) {
        specializationsService.deleteSpecialization(specializationId);
        return specializationsService.getSpecializationsList();
    }

    @RequestMapping(value = "/specializations", method = RequestMethod.POST)
    public List<SpecializationsDto> editSpecialization(@RequestBody final SpecializationsDto params) {
        specializationsService.updateSpecialization(params);
        return specializationsService.getSpecializationsList();
    }

}
