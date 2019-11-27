package com.tsystems.javaschool.medical.backend.controller;

import com.tsystems.javaschool.medical.backend.dto.DrugDto;
import com.tsystems.javaschool.medical.backend.services.DrugService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class DrugController {
    private final DrugService drugService;

    public DrugController(DrugService drugService) {
        this.drugService = drugService;
    }

    @GetMapping(value = "/drugs")
    public List<DrugDto> getPatientsList() {
        return drugService.getAll();
    }

    @PutMapping(value = "/drugs")
    public void getPatientsList(@RequestBody DrugDto params ) {
        drugService.add(params);
    }


}
