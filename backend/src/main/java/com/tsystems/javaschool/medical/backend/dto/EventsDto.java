package com.tsystems.javaschool.medical.backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Timestamp;

public class EventsDto implements Serializable, Comparable<EventsDto> {
    @JsonProperty("id")
    private BigInteger id;
    @JsonProperty("patient")
    private PatientsDto patientByPatientId;
    @JsonProperty("procedure")
    private ProceduresDto proceduresByProcedureId;
    @JsonProperty("room")
    private RoomsDto roomsByRoomId;
    @JsonProperty("staff")
    private StaffDto staffByStaffId;
    @JsonProperty("status")
    private String status;
    @JsonProperty("start_date")
    private Timestamp startDate;
    @JsonProperty("end_date")
    private Timestamp endDate;
    @JsonProperty("created_at")
    private Timestamp createdAt;
    @JsonProperty("updated_at")
    private Timestamp updatedAt;
    @JsonProperty("deleted")
    private String deleted;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public PatientsDto getPatientByPatientId() {
        return patientByPatientId;
    }

    public void setPatientByPatientId(PatientsDto patientByPatientId) {
        this.patientByPatientId = patientByPatientId;
    }

    public ProceduresDto getProceduresByProcedureId() {
        return proceduresByProcedureId;
    }

    public void setProceduresByProcedureId(ProceduresDto proceduresByProcedureId) {
        this.proceduresByProcedureId = proceduresByProcedureId;
    }

    public RoomsDto getRoomsByRoomId() {
        return roomsByRoomId;
    }

    public void setRoomsByRoomId(RoomsDto roomsByRoomId) {
        this.roomsByRoomId = roomsByRoomId;
    }

    public StaffDto getStaffByStaffId() {
        return staffByStaffId;
    }

    public void setStaffByStaffId(StaffDto staffByStaffId) {
        this.staffByStaffId = staffByStaffId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
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

    @Override
    public int compareTo(EventsDto o) {
        return 0;
    }
}
