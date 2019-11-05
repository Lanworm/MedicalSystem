package com.tsystems.javaschool.medical.backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tsystems.javaschool.medical.backend.entities.PatientsEntity;
import com.tsystems.javaschool.medical.backend.entities.ProceduresEntity;
import com.tsystems.javaschool.medical.backend.entities.RoomsEntity;
import com.tsystems.javaschool.medical.backend.entities.StaffEntity;

import java.sql.Timestamp;

public class EventsDto {
    @JsonProperty("id")
    private int id;
    @JsonProperty("patient")
    private PatientsEntity patientsByPatientId;
    @JsonProperty("procedure")
    private ProceduresEntity proceduresByProcedureId;
    @JsonProperty("room")
    private RoomsEntity roomsByRoomId;
    @JsonProperty("staff")
    private StaffEntity staffByStaffId;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public PatientsEntity getPatientsByPatientId() {
        return patientsByPatientId;
    }

    public void setPatientsByPatientId(PatientsEntity patientsByPatientId) {
        this.patientsByPatientId = patientsByPatientId;
    }

    public ProceduresEntity getProceduresByProcedureId() {
        return proceduresByProcedureId;
    }

    public void setProceduresByProcedureId(ProceduresEntity proceduresByProcedureId) {
        this.proceduresByProcedureId = proceduresByProcedureId;
    }

    public RoomsEntity getRoomsByRoomId() {
        return roomsByRoomId;
    }

    public void setRoomsByRoomId(RoomsEntity roomsByRoomId) {
        this.roomsByRoomId = roomsByRoomId;
    }

    public StaffEntity getStaffByStaffId() {
        return staffByStaffId;
    }

    public void setStaffByStaffId(StaffEntity staffByStaffId) {
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
}
