package com.tsystems.javaschool.medical.backend.dto.prescriptions;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tsystems.javaschool.medical.backend.dto.DrugDto;
import com.tsystems.javaschool.medical.backend.dto.PatientsDto;
import com.tsystems.javaschool.medical.backend.dto.ProceduresDto;

import java.math.BigInteger;
import java.sql.Timestamp;

public class PrescriptionDto {

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
    @JsonProperty("patient")
    private PatientsDto patientsByPatientId;
    @JsonProperty("procedure")
    private ProceduresDto proceduresByProcedureId;
    @JsonProperty("drug")
    private DrugDto drugsByDrugId;

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

    public PatientsDto getPatientsByPatientId() {
        return patientsByPatientId;
    }

    public void setPatientsByPatientId(PatientsDto patientsByPatientId) {
        this.patientsByPatientId = patientsByPatientId;
    }

    public ProceduresDto getProceduresByProcedureId() {
        return proceduresByProcedureId;
    }

    public void setProceduresByProcedureId(ProceduresDto proceduresByProcedureId) {
        this.proceduresByProcedureId = proceduresByProcedureId;
    }

    public DrugDto getDrugsByDrugId() {
        return drugsByDrugId;
    }

    public void setDrugsByDrugId(DrugDto drugsByDrugId) {
        this.drugsByDrugId = drugsByDrugId;
    }
}
