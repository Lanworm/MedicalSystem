package com.tsystems.javaschool.medical.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tsystems.javaschool.medical.entities.DoctorEntity;

import java.io.Serializable;

public class DoctorDto implements Serializable {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("first_name")
    private String firstName;
    @JsonProperty("second_name")
    private String secondName;
    @JsonProperty("last_name")
    private String lastName;

    public DoctorDto(DoctorEntity a) {
        this.id = a.getId();
        this.firstName = a.getFirstName();
        this.secondName = a.getSecondName();
        this.lastName = a.getLastName();
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
