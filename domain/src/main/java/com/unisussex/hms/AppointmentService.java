package com.unisussex.hms;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface AppointmentService {
    List<Appointment> getAllAppointments();

    //Optional<Appointment> getAppointments(String appointment);

    Optional<Appointment> findByPatientIdAndDate(Long patientId, Date appointmentDate);

    List<Appointment> getAppointmentByParam(String param);

    Appointment saveAppointment(Appointment appointment);

    Appointment updateAppointment(Appointment appointmentDto);

    void saveAppointments(List<Appointment> appointments);

    void delete(long id);

    void deleteAll();
}
