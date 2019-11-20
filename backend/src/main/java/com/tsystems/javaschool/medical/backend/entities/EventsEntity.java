package com.tsystems.javaschool.medical.backend.entities;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "events", schema = "public" )
public class EventsEntity {
    private int id;
    private PatientsEntity patientsByPatientId;
    private ProceduresEntity proceduresByProcedureId;
    private RoomsEntity roomsByRoomId;
    private StaffEntity staffByStaffId;
    private String status;
    private Timestamp startDate;
    private Timestamp endDate;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private String deleted;

    @Id
    @javax.persistence.SequenceGenerator(name = "events_item_id", sequenceName = "events_item_id", allocationSize = 1)
    @javax.persistence.GeneratedValue(generator = "events_item_id", strategy = javax.persistence.GenerationType.SEQUENCE)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
        EventsEntity that = (EventsEntity) o;
        return id == that.id &&
                Objects.equals(status, that.status) &&
                Objects.equals(startDate, that.startDate) &&
                Objects.equals(endDate, that.endDate) &&
                Objects.equals(createdAt, that.createdAt) &&
                Objects.equals(updatedAt, that.updatedAt) &&
                Objects.equals(deleted, that.deleted);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, status, startDate, endDate, createdAt, updatedAt, deleted);
    }

    @ManyToOne
    @JoinColumn(name = "patient_id", referencedColumnName = "id", nullable = false)
    public PatientsEntity getPatientByPatientId() {
        return patientsByPatientId;
    }

    public void setPatientByPatientId(PatientsEntity patientsByPatientId) {
        this.patientsByPatientId = patientsByPatientId;
    }

    @ManyToOne
    @JoinColumn(name = "procedure_id", referencedColumnName = "id", nullable = false)
    public ProceduresEntity getProcedureByProcedureId() {
        return proceduresByProcedureId;
    }

    public void setProcedureByProcedureId(ProceduresEntity proceduresByProcedureId) {
        this.proceduresByProcedureId = proceduresByProcedureId;
    }

    @ManyToOne
    @JoinColumn(name = "room_id", referencedColumnName = "id", nullable = false)
    public RoomsEntity getRoomByRoomId() {
        return roomsByRoomId;
    }

    public void setRoomByRoomId(RoomsEntity roomsByRoomId) {
        this.roomsByRoomId = roomsByRoomId;
    }

    @ManyToOne
    @JoinColumn(name = "staff_id", referencedColumnName = "id", nullable = false)
    public StaffEntity getStaffByStaffId() {
        return staffByStaffId;
    }

    public void setStaffByStaffId(StaffEntity staffByStaffId) {
        this.staffByStaffId = staffByStaffId;
    }
}
