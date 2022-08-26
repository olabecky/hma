package com.unisussex.hms;

import com.unisussex.hms.hibernate.LabResultDao;
import com.unisussex.hms.hibernate.LabResultEntity;
import com.unisussex.hms.hibernate.PatientDao;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LabResultServiceImpl implements LabResultsService {


    private LabResultDao labResultDao;

    private final PatientDao patientDao;
    private EntityLabResultConverter entityLabResultConverter;

    //private final HMAConstants hmaConstants;
    public LabResultServiceImpl(LabResultDao labResultDao,
                                PatientDao patientDao,
                                EntityLabResultConverter entityLabResultConverter) {
        this.labResultDao = labResultDao;
        this.patientDao = patientDao;
        this.entityLabResultConverter = entityLabResultConverter;
    }


    @Override
    public List<LabResult> getAllLabResults() {
        return labResultDao.findAll()
                .stream()
                .map(entityLabResultConverter::convert)
                .collect(Collectors.toList());
    }

    @Override
    public List<LabResult> getPatientByParam(String param) {
        return null;
    }

    @Override
    public LabResult saveResult(LabResult labresult) {
        return null;
    }

    @Override
    public LabResult updateLabresult(LabResult labResultDto) {
        return null;
    }

    @Override
    public void saveLabresult(List<LabResult> Labresult) {

    }

    @Override
    public Optional<LabResult> findByPatientIdAndDate(Long patientId, Date diagnosisDate) {
        return labResultDao.findByPatientIdAndDate(patientId, diagnosisDate)
                .map(entityLabResultConverter::convert);
    }


    @Override
    public List<LabResult> getLabresultByParam(String param) {
        return labResultDao.findByParam(param)
                .stream()
                .map(entityLabResultConverter::convert)
                .collect(Collectors.toList());
    }

    @Override
    public LabResult saveLabResult(LabResult labResult) {
        if(labResultDao.findByPatientIdAndDate(labResult.getPatient().getId(), labResult.getDiagnosisDate()).isPresent()) {
            throw new IllegalArgumentException("Lab result already exists for this patient on " + labResult.getDiagnosisDate());
        } else {
            LabResultEntity savedLabResult = labResultDao.save(entityLabResultConverter.convert(labResult));
            return entityLabResultConverter.convert(savedLabResult);
        }
    }

    @Override
    public LabResult updateALabResults(LabResult labresult) {
        Optional<LabResultEntity> labresultEntity = labResultDao.findById(labresult.getId());

        if(labresultEntity.isEmpty()) {
            throw new IllegalArgumentException("No Lab results exists with id: " + labresult.getId());
        } else {
            LabResultEntity entityInDB = labresultEntity.get();
            entityInDB.setDiagnosisDescription(labresult.getDiagnosisDescription());
            entityInDB.setDiagnosisDate(labresult.getDiagnosisDate());
            entityInDB.setPatient(patientDao.findById(labresult.getPatient().getId()).get());
            entityInDB = labResultDao.save(entityInDB);

            return entityLabResultConverter.convert(entityInDB);
        }
    }

    @Override
    public void saveLabResult(List<LabResult> labresult) {
        List<LabResultEntity> labresultEntities = labresult.stream()
                .map(entityLabResultConverter::convert)
                .collect(Collectors.toList());

        labResultDao.saveAll(labresultEntities);
    }

    @Override
    public void delete(long id) {
        labResultDao.deleteById(id);
    }

    @Override
    public void deleteAll() {
        labResultDao.deleteAll();
    }
}
