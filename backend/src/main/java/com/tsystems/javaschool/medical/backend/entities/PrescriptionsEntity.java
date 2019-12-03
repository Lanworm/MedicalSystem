package com.tsystems.javaschool.medical.backend.entities;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "prescriptions", schema = "public", catalog = "med")
public class PrescriptionsEntity {
    private int id;
    private String timePattern;
    private Timestamp startDate;
    private Timestamp endDate;
    private String dosage;
    private String description;
    private Timestamp updatedAt;
    private Timestamp createdAt;
    private String deleted;
    private String type;
    private PatientsEntity patientsByPatientId;
    private ProceduresEntity proceduresByProcedureId;
    private DrugsEntity drugsByDrugId;

    @Id
    @SequenceGenerator(name = "prescriptions_item_id", sequenceName = "prescriptions_item_id", allocationSize = 1)
    @GeneratedValue(generator = "prescriptions_item_id", strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "time_pattern")
    public String getTimePattern() {
        return timePattern;
    }

    public void setTimePattern(String timePattern) {
        this.timePattern = timePattern;
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
    @UpdateTimestamp
    @Column(name = "updated_at")
    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Basic
    @CreationTimestamp
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

    @Basic
    @Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PrescriptionsEntity that = (PrescriptionsEntity) o;
        return id == that.id &&
                Objects.equals(timePattern, that.timePattern) &&
                Objects.equals(startDate, that.startDate) &&
                Objects.equals(endDate, that.endDate) &&
                Objects.equals(dosage, that.dosage) &&
                Objects.equals(description, that.description) &&
                Objects.equals(updatedAt, that.updatedAt) &&
                Objects.equals(createdAt, that.createdAt) &&
                Objects.equals(deleted, that.deleted) &&
                Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, timePattern, startDate, endDate, dosage, description, updatedAt, createdAt, deleted, type);
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
    @JoinColumn(name = "procedure_id", referencedColumnName = "id")
    public ProceduresEntity getProceduresByProcedureId() {
        return proceduresByProcedureId;
    }

    public void setProceduresByProcedureId(ProceduresEntity proceduresByProcedureId) {
        this.proceduresByProcedureId = proceduresByProcedureId;
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
