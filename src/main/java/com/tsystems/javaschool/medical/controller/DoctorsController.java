package com.tsystems.javaschool.medical.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class DoctorsController {

    @RequestMapping(value = "/doctorsList", method = RequestMethod.GET)
    public String printHello() {
        return "12312312";
    }
}

