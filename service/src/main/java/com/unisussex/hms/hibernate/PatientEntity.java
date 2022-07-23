package com.unisussex.hms.hibernate;

import javax.persistence.*;

@Entity
@Table(name = "PATIENT")
public class PatientEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="SEQ_PATIENT")
    @SequenceGenerator(name = "SEQ_PATIENT", sequenceName = "SEQ_PATIENT", allocationSize = 1)
    private Long id;
    private String firstname;
    private String lastname;
    private String email;

    public PatientEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
