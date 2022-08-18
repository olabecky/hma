package com.unisussex.hms;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import java.util.Date;

@JsonDeserialize(builder = AppointmentDto.Builder.class)
public class AppointmentDto {
    private final Long id;
    private final String appointmentDescription;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final Date appointmentDate;

    private final PatientDto patient;

    private final Long patientId;


    public AppointmentDto(Builder builder) {
        this.id = builder.id;
        this.appointmentDescription = builder.appointmentDescription;
        this.appointmentDate = builder.appointmentDate;
        this.patient = builder.patient;
        this.patientId = builder.patientId;
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

    public PatientDto getPatient() {
        return patient;
    }

    public Long getPatientId() {
        return patientId;
    }

    public static Builder aAppointmentDto() {
        return new Builder();
    }

    @JsonPOJOBuilder(withPrefix = "")
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static final class Builder {
        private Long id;
        private String appointmentDescription;
        private Date appointmentDate;

        private PatientDto patient;

        private Long patientId;

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

        public Builder patient(PatientDto patient) {
            this.patient = patient;
            return this;
        }

        public Builder patientId(Long patientId) {
            this.patientId = patientId;
            return this;
        }

        public AppointmentDto build() {
            return new AppointmentDto(this);
        }
    }

    @Override
    public String toString() {
        return "AppointmentDto{" +
                "id=" + id +
                ", appointmentDescription='" + appointmentDescription + '\'' +
                ", appointmentDate=" + appointmentDate +
                ", patient=" + patient +
                ", patientId=" + patientId +
                '}';
    }
}
