package com.tsystems.javaschool.medical.backend.controller;

import com.tsystems.javaschool.medical.backend.dto.PrescriptionDto;
import com.tsystems.javaschool.medical.backend.services.PrescriptionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PrescriptionController {

    private final PrescriptionService prescriptionService;

    public PrescriptionController(PrescriptionService prescriptionService) {
        this.prescriptionService = prescriptionService;
    }

    @GetMapping(value = "/prescriptions")
    public List<PrescriptionDto> getList() {
        return prescriptionService.getAll();
    }

    @GetMapping(value = "/prescriptionsByPatient")
    public List<PrescriptionDto> getListByPatient(@RequestParam(value = "id") int id) {
        return prescriptionService.getListByPatient(id);
    }
}
