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
                .phoneNumber(entity.getPhoneNumber())
                .address(entity.getAddress())
                .build();
    }

    public PatientEntity convert(Patient patient) {
        PatientEntity patientEntity = new PatientEntity();
        patientEntity.setId(patient.getId());
        patientEntity.setFirstname(patient.getFirstname());
        patientEntity.setLastname(patient.getLastname());
        patientEntity.setEmail(patient.getEmail());
        patientEntity.setPhoneNumber(patient.getPhoneNumber());
        patientEntity.setAddress(patient.getAddress());

        return patientEntity;
    }
}
