package com.unisussex.hms;

import com.unisussex.hms.hibernate.PatientDao;
import com.unisussex.hms.hibernate.PatientEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PatientServiceImpl implements PatientService{

    private final PatientDao patientDao;
    private final EntityPatientConverter entityPatientConverter;

    public PatientServiceImpl(PatientDao patientDao,
                              EntityPatientConverter entityPatientConverter) {
        this.patientDao = patientDao;
        this.entityPatientConverter = entityPatientConverter;
    }

    @Override
    public List<Patient> getAllPatients() {
        return patientDao.findAll()
                .stream()
                .map(entityPatientConverter::convert)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Patient> getPatientByEmail(String email) {
        return patientDao.findByEmail(email)
                .map(entityPatientConverter::convert);
    }

    @Override
    public List<Patient> getPatientByParam(String param) {
        return patientDao.findByParam(param)
                .stream()
                .map(entityPatientConverter::convert)
                .collect(Collectors.toList());
    }

    @Override
    public Patient savePatient(Patient patient) {
        if(patientDao.findByEmail(patient.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Patient already exists with email: " + patient.getEmail());
        } else {
            PatientEntity savedPatient = patientDao.save(entityPatientConverter.convert(patient));

            return entityPatientConverter.convert(savedPatient);
        }
    }

    @Override
    public Patient updatePatient(Patient patient) {
        Optional<PatientEntity> patientEntity = patientDao.findById(patient.getId());

        if(patientEntity.isEmpty()) {
            throw new IllegalArgumentException("No Patient exists with id: " + patient.getId());
        } else {
            PatientEntity entityInDB = patientEntity.get();
            entityInDB.setFirstname(patient.getFirstname());
            entityInDB.setLastname(patient.getLastname());
            entityInDB.setEmail(patient.getEmail());
            entityInDB.setPhoneNumber(patient.getPhoneNumber());
            entityInDB.setAddress(patient.getAddress());
            entityInDB.setPostCode(patient.getPostCode());

            entityInDB = patientDao.save(entityInDB);

            return entityPatientConverter.convert(entityInDB);
        }
    }

    @Override
    public void savePatients(List<Patient> patients) {
        List<PatientEntity> patientEntities = patients.stream()
                .map(entityPatientConverter::convert)
                .collect(Collectors.toList());

        patientDao.saveAll(patientEntities);
    }

    @Override
    public void delete(long id) {
        patientDao.deleteById(id);
    }

    @Override
    public void deleteAll() {
        patientDao.deleteAll();
    }
}
