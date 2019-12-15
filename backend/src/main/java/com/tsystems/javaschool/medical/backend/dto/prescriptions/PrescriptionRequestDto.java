package com.tsystems.javaschool.medical.backend.dto.prescriptions;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigInteger;
import java.sql.Timestamp;

public class PrescriptionRequestDto {
    @JsonProperty("id")
    private BigInteger id;
    @JsonProperty("time_pattern")
    private String timePattern;
    @JsonProperty("start_date")
    private Timestamp startDate;
    @JsonProperty("end_date")
    private Timestamp endDate;
    @JsonProperty("dosage")
    private String dosage;
    @JsonProperty("description")
    private String description;
    @JsonProperty("updated_at")
    private Timestamp updatedAt;
    @JsonProperty("created_at")
    private Timestamp createdAt;
    @JsonProperty("deleted")
    private String deleted;
    @JsonProperty("type")
    private String type;
    @JsonProperty("patient_id")
    private BigInteger patientId;
    @JsonProperty("procedure_id")
    private BigInteger procedureId;
    @JsonProperty("drug_id")
    private BigInteger drugId;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getTimePattern() {
        return timePattern;
    }

    public void setTimePattern(String timePattern) {
        this.timePattern = timePattern;
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

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public BigInteger getDrugId() {
        return drugId;
    }

    public void setDrugId(BigInteger drugId) {
        this.drugId = drugId;
    }
}
