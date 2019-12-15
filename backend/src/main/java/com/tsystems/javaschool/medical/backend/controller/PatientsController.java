package com.tsystems.javaschool.medical.backend.controller;

import com.tsystems.javaschool.medical.backend.dto.PatientsDto;
import com.tsystems.javaschool.medical.backend.services.PatientsService;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PatientsController {


    private final PatientsService patientsService;

    public PatientsController(PatientsService patientsService) {
        this.patientsService = patientsService;
    }

    @GetMapping(value = "/patients")
    public List<PatientsDto> getPatientsList() {
        return patientsService.getPatientsList();
    }

    @GetMapping(value = "/patient/{id}")
    public PatientsDto getPatientById(@PathVariable("id") final BigInteger id) {
        return patientsService.getPatientById(id);
    }

    @PutMapping(value = "/patients")
    public List<PatientsDto> addPatients(
            @RequestParam(value = "first_name") String firstName,
            @RequestParam(value = "second_name") String secondName,
            @RequestParam(value = "last_name") String lastName,
            @RequestParam(value = "insurance_number") String insuranceNumber
    ) {
        patientsService.addPatient(firstName, secondName, lastName, insuranceNumber);
        return patientsService.getPatientsList();
    }

    @DeleteMapping(value = "/patients/{id}")
    public List<PatientsDto> deletePatients(@PathVariable("id") final BigInteger Id) {
        patientsService.deletePatient(Id);
        return patientsService.getPatientsList();
    }

    @PostMapping(value = "/patients")
    public List<PatientsDto> editPatients(@RequestBody final PatientsDto params) {
        patientsService.updatePatient(params);
        return patientsService.getPatientsList();
    }
}
