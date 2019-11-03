package com.tsystems.javaschool.medical.backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DiagnosesDto {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("description")
    private String description;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String descroption) {
        this.description = descroption;
    }
}
