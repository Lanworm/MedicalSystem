package com.tsystems.javaschool.medical.backend.entities;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "prescriptions", schema = "public" )
public class PrescriptionsEntity {
    private int id;
    private Integer patientId;
    private Timestamp startDate;
    private Timestamp endDate;
    private String dosage;
    private String description;
    private Timestamp updatedAt;
    private Timestamp createdAt;
    private String deleted;
    private DrugsEntity drugsByDrugId;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "patient_id")
    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    @Basic
    @Column(name = "start_date")
    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    @Basic
    @Column(name = "end_date")
    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    @Basic
    @Column(name = "dosage")
    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "updated_at")
    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Basic
    @Column(name = "created_at")
    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @Basic
    @Column(name = "deleted")
    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PrescriptionsEntity that = (PrescriptionsEntity) o;
        return id == that.id &&
                Objects.equals(patientId, that.patientId) &&
                Objects.equals(startDate, that.startDate) &&
                Objects.equals(endDate, that.endDate) &&
                Objects.equals(dosage, that.dosage) &&
                Objects.equals(description, that.description) &&
                Objects.equals(updatedAt, that.updatedAt) &&
                Objects.equals(createdAt, that.createdAt) &&
                Objects.equals(deleted, that.deleted);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, patientId, startDate, endDate, dosage, description, updatedAt, createdAt, deleted);
    }

    @ManyToOne
    @JoinColumn(name = "drug_id", referencedColumnName = "id")
    public DrugsEntity getDrugsByDrugId() {
        return drugsByDrugId;
    }

    public void setDrugsByDrugId(DrugsEntity drugsByDrugId) {
        this.drugsByDrugId = drugsByDrugId;
    }
}
