package com.tsystems.javaschool.medical.backend.entities;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "rooms", schema = "public", catalog = "med")
public class RoomsEntity {
    private int id;
    private String description;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private String deleted;
//    private List<EventsEntity> eventsByRoom;
//
//    @OneToMany(
//            mappedBy = "roomByRoomId",
//            cascade = CascadeType.ALL,
//            orphanRemoval = true
//    )
//    public List<EventsEntity> getEventsByRoom() {
//        return eventsByRoom;
//    }
//
//    public void setEventsByRoom(List<EventsEntity> eventsByRoom) {
//        this.eventsByRoom = eventsByRoom;
//    }

    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "rooms_item_id", sequenceName = "rooms_item_id", allocationSize = 1)
    @GeneratedValue(generator = "rooms_item_id", strategy = GenerationType.SEQUENCE)
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
        RoomsEntity that = (RoomsEntity) o;
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
