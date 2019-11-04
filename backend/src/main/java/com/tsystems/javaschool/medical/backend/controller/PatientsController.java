package com.tsystems.javaschool.medical.backend.controller;

import com.tsystems.javaschool.medical.backend.dto.PatientsDto;
import com.tsystems.javaschool.medical.backend.services.PatientsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PatientsController {

    @Autowired
    private PatientsService patientsService;

    @RequestMapping(value = "/patients", method = RequestMethod.GET)
    public List<PatientsDto> getPatientsList() {
        return patientsService.getPatientsList();
    }

    @RequestMapping(value = "/patients", method = RequestMethod.PUT)
    public List<PatientsDto> addPatients(
            @RequestParam(value = "first_name") String firstName,
            @RequestParam(value = "second_name") String secondName,
            @RequestParam(value = "last_name") String lastName,
            @RequestParam(value = "insurance_number") String insuranceNumber
    ) {
        patientsService.addPatient(firstName, secondName, lastName, insuranceNumber);
        return patientsService.getPatientsList();
    }

    @RequestMapping(value = "/patients/{id}", method = RequestMethod.DELETE)
    public List<PatientsDto> deletePatients(@PathVariable("id") final int Id) {
        patientsService.deletePatient(Id);
        return patientsService.getPatientsList();
    }

    @RequestMapping(value = "/patients", method = RequestMethod.POST)
    public List< PatientsDto> editPatients(@RequestBody final  PatientsDto params) {
        patientsService.updatePatient(params);
        return patientsService.getPatientsList();
    }
}
