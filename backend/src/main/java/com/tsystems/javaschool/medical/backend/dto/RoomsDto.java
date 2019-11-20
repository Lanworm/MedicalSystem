package com.tsystems.javaschool.medical.backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class RoomsDto implements Serializable, Comparable<RoomsDto> {
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("description")
    private String description;
    @JsonProperty("deleted")
    private String deleted;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }

    @Override
    public int compareTo(RoomsDto o) {
        return 0;
    }
}
