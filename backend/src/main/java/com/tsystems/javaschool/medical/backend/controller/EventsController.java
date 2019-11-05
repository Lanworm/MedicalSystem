package com.tsystems.javaschool.medical.backend.controller;

import com.tsystems.javaschool.medical.backend.dto.EventsDto;
import com.tsystems.javaschool.medical.backend.services.EventsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EventsController {

    @Autowired
    private EventsService eventsService;

    @RequestMapping(value = "/events", method = RequestMethod.GET)
    public List<EventsDto> getPatientsList() {
        return eventsService.getEventsList();
    }

//    @RequestMapping(value = "/patients", method = RequestMethod.PUT)
//    public List<PatientsDto> addPatients(
//            @RequestParam(value = "first_name") String firstName,
//            @RequestParam(value = "second_name") String secondName,
//            @RequestParam(value = "last_name") String lastName,
//            @RequestParam(value = "insurance_number") String insuranceNumber
//    ) {
//        patientsService.addPatient(firstName, secondName, lastName, insuranceNumber);
//        return patientsService.getPatientsList();
//    }
//
//    @RequestMapping(value = "/patients/{id}", method = RequestMethod.DELETE)
//    public List<PatientsDto> deletePatients(@PathVariable("id") final int Id) {
//        patientsService.deletePatient(Id);
//        return patientsService.getPatientsList();
//    }
//
//    @RequestMapping(value = "/patients", method = RequestMethod.POST)
//    public List< PatientsDto> editPatients(@RequestBody final  PatientsDto params) {
//        patientsService.updatePatient(params);
//        return patientsService.getPatientsList();
//    }
}
