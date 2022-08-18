package com.unisussex.hms;

import com.unisussex.hms.hibernate.AppointmentDao;
import com.unisussex.hms.hibernate.AppointmentEntity;
import com.unisussex.hms.hibernate.PatientDao;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AppointmentServiceImpl implements AppointmentService{

    private final AppointmentDao appointmentDao;

    private final PatientDao patientDao;
    private final EntityAppointmentConverter entityAppointmentConverter;

    //private final HMAConstants hmaConstants;
    public AppointmentServiceImpl(AppointmentDao appointmentDao,
                                  PatientDao patientDao,
                                  EntityAppointmentConverter entityAppointmentConverter) {
        this.appointmentDao = appointmentDao;
        this.patientDao = patientDao;
        this.entityAppointmentConverter = entityAppointmentConverter;
    }


    @Override
    public List<Appointment> getAllAppointments() {
        return appointmentDao.findAll()
                .stream()
                .map(entityAppointmentConverter::convert)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Appointment> findByPatientIdAndDate(Long patientId, Date appointmentDate) {
        return appointmentDao.findByPatientIdAndDate(patientId, appointmentDate)
                .map(entityAppointmentConverter::convert);
    }

    @Override
    public List<Appointment> getAppointmentByParam(String param) {
        return appointmentDao.findByParam(param)
                .stream()
                .map(entityAppointmentConverter::convert)
                .collect(Collectors.toList());
    }

    @Override
    public Appointment saveAppointment(Appointment appointment) {
        if(appointmentDao.findByPatientIdAndDate(appointment.getPatient().getId(), appointment.getAppointmentDate()).isPresent()) {
            throw new IllegalArgumentException("Appointment already exists for this patient on " + appointment.getAppointmentDate());
        } else {
            AppointmentEntity savedAppointment = appointmentDao.save(entityAppointmentConverter.convert(appointment));

            return entityAppointmentConverter.convert(savedAppointment);
        }
    }

    @Override
    public Appointment updateAppointment(Appointment appointment) {
        Optional<AppointmentEntity> appointmentEntity = appointmentDao.findById(appointment.getId());

        if(appointmentEntity.isEmpty()) {
            throw new IllegalArgumentException("No Appointment exists with id: " + appointment.getId());
        } else {
            AppointmentEntity entityInDB = appointmentEntity.get();
            entityInDB.setAppointmentDescription(appointment.getAppointmentDescription());
            entityInDB.setAppointmentDate(appointment.getAppointmentDate());
            entityInDB.setPatient(patientDao.findById(appointment.getPatient().getId()).get());
            entityInDB = appointmentDao.save(entityInDB);

            return entityAppointmentConverter.convert(entityInDB);
        }
    }

    @Override
    public void saveAppointments(List<Appointment> appointments) {
        List<AppointmentEntity> appointmentEntities = appointments.stream()
                .map(entityAppointmentConverter::convert)
                .collect(Collectors.toList());

        appointmentDao.saveAll(appointmentEntities);
    }

    @Override
    public void delete(long id) {
        appointmentDao.deleteById(id);
    }

    @Override
    public void deleteAll() {
        appointmentDao.deleteAll();
    }
}
