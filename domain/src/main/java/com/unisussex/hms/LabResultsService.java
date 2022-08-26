package com.unisussex.hms;

import java.util.Date;
import java.util.List;
import java.util.Optional;


public interface LabResultsService {
    List<LabResult> getAllLabResults();

    List<LabResult> getPatientByParam(String param);

    LabResult saveResult(LabResult labresult);

    LabResult updateLabresult(LabResult labResultDto);

    void saveLabresult(List<LabResult> Labresult);

    Optional<LabResult> findByPatientIdAndDate(Long patientId, Date diagnosisDate);

    List<LabResult> getLabresultByParam(String param);

    LabResult saveLabResult(LabResult labresult);

    LabResult updateALabResults(LabResult labresult);

    void saveLabResult(List<LabResult> labresult);

    void delete(long id);

    void deleteAll();
}
