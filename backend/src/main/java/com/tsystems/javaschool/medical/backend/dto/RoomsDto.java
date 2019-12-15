package com.tsystems.javaschool.medical.backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.math.BigInteger;

public class RoomsDto implements Serializable, Comparable<RoomsDto> {
    @JsonProperty("id")
    private BigInteger id;
    @JsonProperty("description")
    private String description;
    @JsonProperty("deleted")
    private String deleted;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
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
