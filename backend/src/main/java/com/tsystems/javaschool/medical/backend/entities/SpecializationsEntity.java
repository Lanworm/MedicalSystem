package com.tsystems.javaschool.medical.backend.entities;

import com.tsystems.javaschool.medical.backend.entities.enums.IsDeleted;

import javax.persistence.*;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "specializations", schema = "public" )
public class SpecializationsEntity {
    private BigInteger id;
    private String description;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private IsDeleted deleted;

    @Id
    @Column(name = "id")
    @javax.persistence.SequenceGenerator(name = "specializations_item_id", sequenceName = "specializations_item_id", allocationSize = 1)
    @javax.persistence.GeneratedValue(generator = "specializations_item_id", strategy = javax.persistence.GenerationType.SEQUENCE)
    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
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
        SpecializationsEntity that = (SpecializationsEntity) o;
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
}
