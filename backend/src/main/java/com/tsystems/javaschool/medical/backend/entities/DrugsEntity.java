package com.tsystems.javaschool.medical.backend.entities;

import com.tsystems.javaschool.medical.backend.entities.enums.IsDeleted;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "drugs", schema = "public")
public class DrugsEntity {
    private BigInteger id;
    private String description;
    private String type;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private IsDeleted deleted;

    @Id
    @SequenceGenerator(name = "drugs_item_id", sequenceName = "drugs_item_id", allocationSize = 1)
    @GeneratedValue(generator = "drugs_item_id", strategy = javax.persistence.GenerationType.SEQUENCE)
    @Column(name = "id")
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
    @Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
    @UpdateTimestamp
    @Column(name = "updated_at")
    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Basic
    @Enumerated(EnumType.STRING)
    @Column(name = "deleted" )
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
        DrugsEntity that = (DrugsEntity) o;
        return id == that.id &&
                Objects.equals(description, that.description) &&
                Objects.equals(type, that.type) &&
                Objects.equals(createdAt, that.createdAt) &&
                Objects.equals(updatedAt, that.updatedAt) &&
                Objects.equals(deleted, that.deleted);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, type, createdAt, updatedAt, deleted);
    }
}
