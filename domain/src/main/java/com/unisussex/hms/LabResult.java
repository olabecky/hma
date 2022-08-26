package com.unisussex.hms;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class LabResult {
    private final Long id;
    private final String diagnosisDescription;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final Date diagnosisDate;
    private final Patient patient;

    public LabResult(LabResult.Builder builder) {
        this.id = builder.id;
        this.diagnosisDescription = builder.diagnosisDescription;
        this.diagnosisDate = builder.diagnosisDate;
        this.patient = builder.patient;

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

    public Patient getPatient() {
        return patient;
    }



    public static LabResult.Builder aLabresult() {
        return new LabResult.Builder();
    }

    public static final class Builder {
        private Long id;
        private String diagnosisDescription;
        private Date diagnosisDate;
        private  Patient patient;


        private Builder() {
        }

        public static LabResult.Builder aLabresult() {
            return new LabResult.Builder();
        }

        public LabResult.Builder id(Long id) {
            this.id = id;
            return this;
        }


        public LabResult.Builder diagnosisDescription(String diagnosisDescription) {
            this.diagnosisDescription = diagnosisDescription;
            return this;
        }

        public LabResult.Builder diagnosisDate(Date diagnosisDate) {
            this.diagnosisDate = diagnosisDate;
            return this;
        }


        public LabResult.Builder patient(Patient patient) {
            this.patient = patient;
            return this;
        }

        public LabResult build() {
            return new LabResult(this);
        }


    }
}
