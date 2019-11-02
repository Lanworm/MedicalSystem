package com.tsystems.javaschool.medical.backend.entities;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "therapy_log", schema = "public", catalog = "med")
public class TherapyLogEntity {
    private int id;
    private String description;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private String deleted;
    private PatientsEntity patientsByPatientId;
    private AnamnesisEntity anamnesisByAnamnesId;
    private PrescriptionsEntity prescriptionsByPrescriptionnId;
    private DiagnosesEntity diagnosesByDiagnoseId;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
    @Column(name = "created_at")
    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
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
        TherapyLogEntity that = (TherapyLogEntity) o;
        return id == that.id &&
                Objects.equals(description, that.description) &&
                Objects.equals(createdAt, that.createdAt) &&
                Objects.equals(updatedAt, that.updatedAt) &&
                Objects.equals(deleted, that.deleted);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, createdAt, updatedAt, deleted);
    }

    @ManyToOne
    @JoinColumn(name = "patient_id", referencedColumnName = "id")
    public PatientsEntity getPatientsByPatientId() {
        return patientsByPatientId;
    }

    public void setPatientsByPatientId(PatientsEntity patientsByPatientId) {
        this.patientsByPatientId = patientsByPatientId;
    }

    @ManyToOne
    @JoinColumn(name = "anamnes_id", referencedColumnName = "id")
    public AnamnesisEntity getAnamnesisByAnamnesId() {
        return anamnesisByAnamnesId;
    }

    public void setAnamnesisByAnamnesId(AnamnesisEntity anamnesisByAnamnesId) {
        this.anamnesisByAnamnesId = anamnesisByAnamnesId;
    }

    @ManyToOne
    @JoinColumn(name = "prescriptionn_id", referencedColumnName = "id")
    public PrescriptionsEntity getPrescriptionsByPrescriptionnId() {
        return prescriptionsByPrescriptionnId;
    }

    public void setPrescriptionsByPrescriptionnId(PrescriptionsEntity prescriptionsByPrescriptionnId) {
        this.prescriptionsByPrescriptionnId = prescriptionsByPrescriptionnId;
    }

    @ManyToOne
    @JoinColumn(name = "diagnose_id", referencedColumnName = "Id")
    public DiagnosesEntity getDiagnosesByDiagnoseId() {
        return diagnosesByDiagnoseId;
    }

    public void setDiagnosesByDiagnoseId(DiagnosesEntity diagnosesByDiagnoseId) {
        this.diagnosesByDiagnoseId = diagnosesByDiagnoseId;
    }
}
