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
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping(value = "/patient")
    public List<PatientDto> getAllPatients() {
        System.out.println(":::::::::fetching all::::::: ");
        return this.patientService.getAllPatients().stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }

    @GetMapping("/patient/{email}")
    public PatientDto getPatientByEmail(@PathVariable("email") String email) {
        System.out.println(":::::::::email::::::: "+email);
        return this.patientService.getPatientByEmail(email)
                .map(this::convert)
                .orElseThrow(() -> new ResourceNotFoundException("Not patient with email '" + email + "'"));
    }

    @GetMapping("/patientSearch")
    public List<PatientDto> getPatientByParam(@RequestParam(name = "param") String param) {
        System.out.println(":::::::::param::::::: "+param);
        if (Strings.isBlank(param)) {
            return this.patientService.getAllPatients().stream()
                    .map(this::convert)
                    .collect(Collectors.toList());
        }
        return this.patientService.getPatientByParam("%"+param.toLowerCase()+"%").stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }

    @PostMapping(value = "/patient")
    public ResponseEntity<PatientDto> savePatient(@RequestBody PatientDto patientDto) {

        if (patientDto.getId() != null) {
            throw new IllegalArgumentException("To update an existing patient you should be performing a PUT. To create a patient, remove the ID as an ID will be assigned at creation.");
        }

        if (Strings.isBlank(patientDto.getFirstname())) {
            throw new IllegalArgumentException("Firstname was empty/null.");
        }

        if (Strings.isBlank(patientDto.getLastname())) {
            throw new IllegalArgumentException("Lastname was empty/null.");
        }

        if (Strings.isBlank(patientDto.getEmail())) {
            throw new IllegalArgumentException("Email was empty/null.");
        }

        if (Strings.isBlank(patientDto.getPhoneNumber())) {
            throw new IllegalArgumentException("Phone number was empty/null.");
        }

        if (Strings.isBlank(patientDto.getAddress())) {
            throw new IllegalArgumentException("Address was empty/null.");
        }

        Patient createdPatient = this.patientService.savePatient(Patient.aPatient()
                .id(patientDto.getId())
                .firstname(patientDto.getFirstname())
                .lastname(patientDto.getLastname())
                .email(patientDto.getEmail())
                .phoneNumber(patientDto.getPhoneNumber())
                .address(patientDto.getAddress())
                .build());

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(convert(createdPatient));
    }

    @PutMapping(value = "/patient/{id}")
    public ResponseEntity<PatientDto> updatePatient(@PathVariable("id") long id, @RequestBody PatientDto patient) {

        if (Strings.isBlank(patient.getFirstname())) {
            throw new IllegalArgumentException("Firstname was empty/null.");
        }

        if (Strings.isBlank(patient.getLastname())) {
            throw new IllegalArgumentException("Lastname was empty/null.");
        }

        if (Strings.isBlank(patient.getEmail())) {
            throw new IllegalArgumentException("Email was empty/null.");
        }

        if (Strings.isBlank(patient.getPhoneNumber())) {
            throw new IllegalArgumentException("Phone number was empty/null.");
        }

        if (Strings.isBlank(patient.getAddress())) {
            throw new IllegalArgumentException("Address was empty/null.");
        }

        Patient createdPatient = this.patientService.updatePatient(Patient.aPatient()
                .id(id)
                .firstname(patient.getFirstname())
                .lastname(patient.getLastname())
                .email(patient.getEmail())
                .phoneNumber(patient.getPhoneNumber())
                .address(patient.getAddress())
                .build());

        return ResponseEntity.status(HttpStatus.OK)
                .body(convert(createdPatient));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/patient/{id}")
    public void deletePatient(@PathVariable("id") long id) {
        this.patientService.delete(id);
    }

    private PatientDto convert(Patient patient) {
        return PatientDto.aPatientDto()
                .id(patient.getId())
                .firstname(patient.getFirstname())
                .lastname(patient.getLastname())
                .email(patient.getEmail())
                .phoneNumber(patient.getPhoneNumber())
                .address(patient.getAddress())
                .build();
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
