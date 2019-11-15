package com.tsystems.javaschool.medical.backend.controller;

import com.tsystems.javaschool.medical.backend.dto.StaffDto;
import com.tsystems.javaschool.medical.backend.services.StaffService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class StaffController {

    private final StaffService staffService;

    public StaffController(StaffService staffService) {
        this.staffService = staffService;
    }

    @RequestMapping(value = "/staff", method = RequestMethod.GET)
    public List<StaffDto> getStaffList() {
        return staffService.getStaffList();
    }

    @RequestMapping(value = "/staff", method = RequestMethod.PUT)
    public List<StaffDto> addStaff(
            @RequestParam(value = "first_name") String firstName,
            @RequestParam(value = "second_name") String secondName,
            @RequestParam(value = "last_name") String lastName,
            @RequestParam(value = "specialization_id") int specializationId
    ) {
        staffService.addStaff(firstName, secondName, lastName, specializationId);
        return staffService.getStaffList();
    }

    @RequestMapping(value = "/staff/{id}", method = RequestMethod.DELETE)
    public List<StaffDto> deleteStaff(@PathVariable("id") final int staffId) {
        staffService.deleteStaff(staffId);
        return staffService.getStaffList();
    }

    @RequestMapping(value = "/staff", method = RequestMethod.POST)
    public List<StaffDto> editSpecialization(@RequestBody final StaffDto params) {
        staffService.updateSpecialization(params);
        return staffService.getStaffList();
    }
}
