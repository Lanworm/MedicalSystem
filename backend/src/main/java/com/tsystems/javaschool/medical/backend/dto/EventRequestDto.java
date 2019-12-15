package com.tsystems.javaschool.medical.backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigInteger;
import java.sql.Timestamp;

public class EventRequestDto {
    @JsonProperty("end_date")
    private Timestamp endDate;
    @JsonProperty("start_date")
    private Timestamp startDate;
    @JsonProperty("patient_id")
    private BigInteger patientId;
    @JsonProperty("procedure_id")
    private BigInteger procedureId;
    @JsonProperty("room_id")
    private BigInteger roomId;
    @JsonProperty("staff_id")
    private BigInteger staffId;
    @JsonProperty("status")
    private String status;
    @JsonProperty("id")
    private BigInteger id;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public BigInteger getPatientId() {
        return patientId;
    }

    public void setPatientId(BigInteger patientId) {
        this.patientId = patientId;
    }

    public BigInteger getProcedureId() {
        return procedureId;
    }

    public void setProcedureId(BigInteger procedureId) {
        this.procedureId = procedureId;
    }

    public BigInteger getRoomId() {
        return roomId;
    }

    public void setRoomId(BigInteger roomId) {
        this.roomId = roomId;
    }

    public BigInteger getStaffId() {
        return staffId;
    }

    public void setStaffId(BigInteger staffId) {
        this.staffId = staffId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
