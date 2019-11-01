package com.tsystems.javaschool.medical.entities;
import java.util.Objects;

@javax.persistence.Entity
@javax.persistence.Table(name = "doctors", schema = "public", catalog = "med")
public class DoctorEntity {
    private int id;
    private String firstName;
    private String secondName;
    private String lastName;


    @javax.persistence.Id
    @javax.persistence.Basic
    @javax.persistence.SequenceGenerator(name = "doctors_seq", sequenceName = "doctors_id_seq", allocationSize = 1)
    @javax.persistence.GeneratedValue(generator = "doctors_seq", strategy = javax.persistence.GenerationType.SEQUENCE)
    @javax.persistence.Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @javax.persistence.Basic
    @javax.persistence.Column(name = "first_name")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @javax.persistence.Basic
    @javax.persistence.Column(name = "second_name")
    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    @javax.persistence.Basic
    @javax.persistence.Column(name = "last_name")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DoctorEntity that = (DoctorEntity) o;

        if (id != that.id) return false;
        if (!Objects.equals(firstName, that.firstName)) return false;
        if (!Objects.equals(secondName, that.secondName)) return false;
        if (!Objects.equals(lastName, that.lastName)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (secondName != null ? secondName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        return result;
    }
}
