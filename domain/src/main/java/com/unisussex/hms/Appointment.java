package com.unisussex.hms;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Appointment {
    private final Long id;
    private final String appointmentDescription;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final Date appointmentDate;
    private final Patient patient;


    public Appointment(Builder builder) {
        this.id = builder.id;
        this.appointmentDescription = builder.appointmentDescription;
        this.appointmentDate = builder.appointmentDate;
        this.patient = builder.patient;

    }

    public Long getId() {
        return id;
    }

    public String getAppointmentDescription() {
        return appointmentDescription;
    }

    public Date getAppointmentDate() {
        return appointmentDate;
    }

    public Patient getPatient() {
        return patient;
    }

    public static Builder aAppointment() {
        return new Builder();
    }

    public static final class Builder {
        private Long id;
        private String appointmentDescription;
        private Date appointmentDate;
        private  Patient patient;


        private Builder() {
        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder appointmentDescription(String appointmentDescription) {
            this.appointmentDescription = appointmentDescription;
            return this;
        }

        public Builder appointmentDate(Date appointmentDate) {
            this.appointmentDate = appointmentDate;
            return this;
        }
        public Builder patient(Patient patient) {
            this.patient = patient;
            return this;
        }

        public Appointment build() {
            return new Appointment(this);
        }
    }
}
