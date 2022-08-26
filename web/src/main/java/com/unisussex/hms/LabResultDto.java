package com.unisussex.hms;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import java.util.Date;

@JsonDeserialize(builder = LabResultDto.Builder.class)
public class LabResultDto {
    private final Long id;
    private final String diagnosisDescription;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final Date diagnosisDate;

    private final PatientDto patient;

    private final Long patientId;


    public LabResultDto(Builder builder) {
        this.id = builder.id;
        this.diagnosisDescription = builder.diagnosisDescription;
        this.diagnosisDate = builder.diagnosisDate;
        this.patient = builder.patient;
        this.patientId = builder.patientId;
    }

    public Long getId() {
        return id;
    }

    public String getDiagnosisDescription() {
        return diagnosisDescription;
    }

    public Date getDiagnosisDate() {
        return diagnosisDate;
    }

    public PatientDto getPatient() {
        return patient;
    }

    public Long getPatientId() {
        return patientId;
    }

    public static Builder aLabResultDto() {
        return new Builder();
    }

    @JsonPOJOBuilder(withPrefix = "")
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static final class Builder {
        private Long id;
        private String diagnosisDescription;
        private Date diagnosisDate;

        private PatientDto patient;

        private Long patientId;

        private Builder() {
        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder diagnosisDescription(String diagnosisDescription) {
            this.diagnosisDescription = diagnosisDescription;
            return this;
        }

        public Builder diagnosisDate(Date diagnosisDate) {
            this.diagnosisDate = diagnosisDate;
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

        public LabResultDto build() {
            return new LabResultDto(this);
        }
    }

    @Override
    public String toString() {
        return "LabResultDto{" +
                "id=" + id +
                ", diagnosisDescription='" + diagnosisDescription + '\'' +
                ", diagnosisDate=" + diagnosisDate +
                ", patient=" + patient +
                ", patientId=" + patientId +
                '}';
    }
}
