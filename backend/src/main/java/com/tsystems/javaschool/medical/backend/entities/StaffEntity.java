package com.tsystems.javaschool.medical.backend.entities;

import com.tsystems.javaschool.medical.backend.entities.enums.IsDeleted;

import javax.persistence.*;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "staff", schema = "public" )
public class StaffEntity {
    private BigInteger id;
    private String firstName;
    private String secondName;
    private String lastName;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private IsDeleted deleted;
    private SpecializationsEntity specializationsBySpecializationId;

    @Id
    @Column(name = "id")
    @javax.persistence.SequenceGenerator(name = "staff_item_id", sequenceName = "staff_item_id", allocationSize = 1)
    @javax.persistence.GeneratedValue(generator = "staff_item_id", strategy = javax.persistence.GenerationType.SEQUENCE)
    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    @Basic
    @Column(name = "first_name")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
    @Column(name = "second_name")
    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    @Basic
    @Column(name = "last_name")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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
    @Enumerated(EnumType.STRING)
    @Column(name = "deleted")
    public IsDeleted getDeleted() {
        return deleted;
    }

    public void setDeleted(IsDeleted deleted) {
        this.deleted = deleted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StaffEntity that = (StaffEntity) o;
        return id == that.id &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(secondName, that.secondName) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(createdAt, that.createdAt) &&
                Objects.equals(updatedAt, that.updatedAt) &&
                Objects.equals(deleted, that.deleted);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, secondName, lastName, createdAt, updatedAt, deleted);
    }

    @ManyToOne
    @JoinColumn(name = "specialization_id", referencedColumnName = "id", nullable = false)
    public SpecializationsEntity getSpecializationsBySpecializationId() {
        return specializationsBySpecializationId;
    }

    public void setSpecializationsBySpecializationId(SpecializationsEntity specializationsBySpecializationId) {
        this.specializationsBySpecializationId = specializationsBySpecializationId;
    }
}
