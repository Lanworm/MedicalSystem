package com.tsystems.javaschool.medical.backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;

public class ScheduleDto {
    @JsonProperty("id")
    private int id;
    @JsonProperty("date")
    private Timestamp date;
    @JsonProperty("start_time")
    private Timestamp startTime;
    @JsonProperty("end_time")
    private Timestamp endTime;
    @JsonProperty("created_at")
    private Timestamp createdAt;
    @JsonProperty("updated_at")
    private Timestamp updatedAt;
    @JsonProperty("deleted")
    private String deleted;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }
}
