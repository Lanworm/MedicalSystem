package com.tsystems.javaschool.medical.controller;

import com.tsystems.javaschool.medical.dto.DoctorDto;
import com.tsystems.javaschool.medical.services.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api")
public class DoctorsController {

    @Autowired
    private DoctorService doctorService;

    @RequestMapping(value = "/doctorsList", method = RequestMethod.GET)
    public List<DoctorDto> printHello() {
        System.out.println(doctorService.getDoctorsList());
        return doctorService.getDoctorsList();
    }
}

