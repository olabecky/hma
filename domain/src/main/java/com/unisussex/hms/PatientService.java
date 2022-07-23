package com.unisussex.hms;

import java.util.List;
import java.util.Optional;

public interface PatientService {
    List<Patient> getAllPatients();

    Optional<Patient> getPatientByEmail(String email);

    List<Patient> getPatientByParam(String param);

    Patient savePatient(Patient patient);

    Patient updatePatient(Patient patientDto);

    void savePatients(List<Patient> patients);

    void delete(long id);

    void deleteAll();
}
