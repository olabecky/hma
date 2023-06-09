package com.unisussex.hms.hibernate;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface LabResultDao extends JpaRepository<LabResultEntity, Long> {

    @Query("SELECT e FROM LabResultEntity e WHERE lower(e.patient.id) = :patientId " +
            "and e.diagnosisDate = :diagnosisDate")
    Optional<LabResultEntity> findByPatientIdAndDate(@Param("patientId") Long patientId, @Param("diagnosisDate") Date diagnosisDate);


    @Query("SELECT e FROM LabResultEntity e WHERE lower(e.patient.email) like :param " +
            "or lower(e.patient.lastname) like :param " +
            "or lower(e.patient.firstname) like :param "+
            "or lower(e.patient.email) like :param "+
            "or lower(e.patient.address) like :param "+
            "or lower(e.patient.phoneNumber) like :param "+
            "or lower(e.diagnosisDescription) like :param"
    )
    List<LabResultEntity> findByParam(@Param("param") String param);
}
