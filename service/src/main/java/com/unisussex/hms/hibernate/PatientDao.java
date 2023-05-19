package com.unisussex.hms.hibernate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PatientDao extends JpaRepository<PatientEntity, Long> {

    Optional<PatientEntity> findByEmail(String email);

    @Query("SELECT e FROM PatientEntity e WHERE lower(e.firstname) like :param" +
            " or lower(e.lastname) like :param" +
            " or lower(e.email) like :param "+
            "or lower(e.address) like :param "+
            "or lower(e.postCode) like :param "+
            "or lower(e.phoneNumber) like :param")
    List<PatientEntity> findByParam(@Param("param") String param);
}
