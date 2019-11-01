package com.tsystems.javaschool.medical.backend.controller;

import com.tsystems.javaschool.medical.backend.dto.DoctorDto;
import com.tsystems.javaschool.medical.backend.services.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
public class DoctorsController {

    @Autowired
    private DoctorService doctorService;


    @RequestMapping(value = "/doctorsList", method = RequestMethod.GET)
    public List<DoctorDto> getDoctorsList() {
        return doctorService.getDoctorsList();
    }

    @RequestMapping(value = "/doctor", method = RequestMethod.PUT)
    public List<DoctorDto> addDoctor(
            @RequestParam(value = "firstName") String firstName,
            @RequestParam(value = "secondName") String secondName,
            @RequestParam(value = "lastName") String lastName
    ) {
        doctorService.addDoctor(firstName, secondName, lastName);
        return doctorService.getDoctorsList();
    }

    @RequestMapping(value = "/doctor/{id}", method = RequestMethod.DELETE)
    public List<DoctorDto> deleteDoctor(@PathVariable("id") final int doctorId) {
        doctorService.deleteDoctor(doctorId);
        return doctorService.getDoctorsList();
    }
}

