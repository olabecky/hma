package com.unisussex.hms;

import com.unisussex.hms.hibernate.LabResultEntity;
import com.unisussex.hms.hibernate.PatientEntity;
import org.springframework.stereotype.Component;

@Component
public class EntityLabResultConverter {
    public LabResult convert(LabResultEntity entity) {
        return LabResult.aLabresult()
                .id(entity.getId())
                .diagnosisDate(entity.getDiagnosisDate())
                .diagnosisDescription(entity.getDiagnosisDescription())
                .patient(Patient.aPatient()
                        .id(entity.getPatient().getId())
                        .address(entity.getPatient().getAddress())
                        .phoneNumber(entity.getPatient().getPhoneNumber())
                        .email(entity.getPatient().getEmail())
                        .lastname(entity.getPatient().getLastname())
                        .firstname(entity.getPatient().getFirstname())
                        .registrationDate(entity.getPatient().getRegistrationDate())
                        .build())
                .build();
    }

    public LabResultEntity convert(LabResult labresult) {
        LabResultEntity labresultEntity = new LabResultEntity();
        labresultEntity.setId(labresult.getId());
        labresultEntity.setDiagnosisDescription(labresult.getDiagnosisDescription());
        labresultEntity.setDiagnosisDate(labresult.getDiagnosisDate());
        labresultEntity.setPatient(new PatientEntity(labresult.getPatient().getId()));

        return labresultEntity;
    }
}
