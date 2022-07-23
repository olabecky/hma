package com.unisussex.hms;

import com.unisussex.hms.hibernate.PatientEntity;
import org.springframework.stereotype.Component;

@Component
public class EntityPatientConverter {
    public Patient convert(PatientEntity entity) {
        return Patient.aPatient()
                .id(entity.getId())
                .firstname(entity.getFirstname())
                .lastname(entity.getLastname())
                .email(entity.getEmail())
                .build();
    }

    public PatientEntity convert(Patient patient) {
        PatientEntity patientEntity = new PatientEntity();
        patientEntity.setId(patient.getId());
        patientEntity.setFirstname(patient.getFirstname());
        patientEntity.setLastname(patient.getLastname());
        patientEntity.setEmail(patient.getEmail());

        return patientEntity;
    }
}
