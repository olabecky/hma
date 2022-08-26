package com.unisussex.hms;

import com.unisussex.hms.exception.ResourceNotFoundException;
import org.apache.logging.log4j.util.Strings;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class LabResultController {

    private final LabResultsService labResultsService;

    public LabResultController(LabResultsService labResultsService) {
        this.labResultsService = labResultsService;
    }

    @GetMapping(value = "/labResults")
    public List<LabResultDto> getAllLabResults() {
        System.out.println(":::::::::fetching all::::::: ");
        return this.labResultsService.getAllLabResults().stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }

/*
    @GetMapping("/labresult/{email}")
    public labresultDto getPatientByEmail(@PathVariable("email") String email) {
        System.out.println(":::::::::email::::::: "+email);
        return this.labresultsService.getPatientByEmail(email)
                .map(this::convert)
                .orElseThrow(() -> new ResourceNotFoundException("Not patient with email '" + email + "'"));
    }*/

    @GetMapping("/labResultSearch")
    public List<LabResultDto> getLabResultByParam(@RequestParam(name = "param", required = false) String param) {
        System.out.println(":::::::::param::::::: "+param);
        if (Strings.isBlank(param)) {
            return this.labResultsService.getAllLabResults().stream()
                    .map(this::convert)
                    .collect(Collectors.toList());
        }
        return this.labResultsService.getPatientByParam("%"+param.toLowerCase()+"%").stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/labResult/{id}")
    public void deleteLabResult(@PathVariable("id") long id) {
        this.labResultsService.delete(id);
    }

    private LabResultDto convert(LabResult labResult) {
        return LabResultDto.aLabResultDto()
                .id(labResult.getId())
                .diagnosisDescription(labResult.getDiagnosisDescription())
                .diagnosisDate(labResult.getDiagnosisDate())
                .patient(PatientDto.aPatientDto()
                        .id(labResult.getPatient().getId())
                        .firstname(labResult.getPatient().getFirstname())
                        .lastname(labResult.getPatient().getLastname())
                        .phoneNumber(labResult.getPatient().getPhoneNumber())
                        .email(labResult.getPatient().getEmail())
                        .address(labResult.getPatient().getAddress()).build())
                .build();
    }

    @PostMapping(value = "/labResults")
    public ResponseEntity<LabResultDto> saveLabResults(@RequestBody LabResultDto labResultDto) {

        if (labResultDto.getId() != null) {
            throw new IllegalArgumentException("To update an existing lab result you should be performing a PUT. To create a Lab result, remove the ID as an ID will be assigned at creation.");
        }

        if (Strings.isBlank(labResultDto.getDiagnosisDescription())) {
            throw new IllegalArgumentException("Diagnosis description was empty/null.");
        }

        if (labResultDto.getDiagnosisDate() == null) {
            throw new IllegalArgumentException("Diagnosis date was empty/null.");
        }

        if (labResultDto.getPatientId() == null) {
            throw new IllegalArgumentException("Patient was empty/null.");
        }

        LabResult createdLabResult = this.labResultsService.saveLabResult(LabResult.aLabresult()
                .id(labResultDto.getId())
                .diagnosisDescription(labResultDto.getDiagnosisDescription())
                .diagnosisDate(labResultDto.getDiagnosisDate())
                .patient(Patient.aPatient().id(labResultDto.getPatientId()).build() )
                .build());

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(convert(createdLabResult));
    }


    @ExceptionHandler(ResourceNotFoundException.class)
    void handleResourceNotFound(HttpServletResponse response, ResourceNotFoundException ex) throws IOException {
        response.sendError(HttpStatus.NOT_FOUND.value(), ex.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    void handleBadRequests(HttpServletResponse response, IllegalArgumentException ex) throws IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }
}
