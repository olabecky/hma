package com.unisussex.hms;

import com.unisussex.hms.hibernate.AppointmentEntity;
import com.unisussex.hms.hibernate.PatientEntity;
import com.unisussex.hms.hibernate.enums.AppointmentStatus;
import org.springframework.stereotype.Component;

@Component
public class EntityAppointmentConverter {
    public Appointment convert(AppointmentEntity entity) {
        return Appointment.aAppointment()
                .id(entity.getId())
                .appointmentDate(entity.getAppointmentDate())
                .appointmentDescription(entity.getAppointmentDescription())
                .patient(Patient.aPatient()
                        .id(entity.getPatient().getId())
                        .address(entity.getPatient().getAddress())
                        .phoneNumber(entity.getPatient().getPhoneNumber())
                        .email(entity.getPatient().getEmail())
                        .lastname(entity.getPatient().getLastname())
                        .firstname(entity.getPatient().getFirstname())
                        .registrationDate(entity.getPatient().getRegistrationDate())
                        .build())
                .status(entity.getStatus().name())
                .build();
    }

    public AppointmentEntity convert(Appointment appointment) {
        AppointmentEntity appointmentEntity = new AppointmentEntity();
        appointmentEntity.setId(appointment.getId());
        appointmentEntity.setAppointmentDescription(appointment.getAppointmentDescription());
        appointmentEntity.setAppointmentDate(appointment.getAppointmentDate());
        appointmentEntity.setPatient(new PatientEntity(appointment.getPatient().getId()));
        appointmentEntity.setStatus(AppointmentStatus.valueOf(appointment.getStatus()));

        return appointmentEntity;
    }
}
