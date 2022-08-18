package com.unisussex.hms.hibernate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface AppointmentDao extends JpaRepository<AppointmentEntity, Long> {

    @Query("SELECT e FROM AppointmentEntity e WHERE lower(e.patient.id) = :patientId " +
            "and lower(e.appointmentDate) = :appointmentDate")
    Optional<AppointmentEntity> findByPatientIdAndDate(@Param("patientId") Long patientId, @Param("appointmentDate") Date appointmentDate);

    @Query("SELECT e FROM AppointmentEntity e WHERE lower(e.patient.email) like :param " +
            "or lower(e.patient.lastname) like :param " +
            "or lower(e.patient.firstname) like :param "+
            "or lower(e.patient.email) like :param "+
            "or lower(e.patient.address) like :param "+
            "or lower(e.patient.phoneNumber) like :param "+
            "or lower(e.appointmentDescription) like :param"
    )
    List<AppointmentEntity> findByParam(@Param("param") String param);
}
