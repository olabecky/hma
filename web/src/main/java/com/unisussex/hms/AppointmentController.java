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
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @GetMapping(value = "/appointment")
    public List<AppointmentDto> getAllAppointments() {
        System.out.println(":::::::::fetching all::::::: ");
        return this.appointmentService.getAllAppointments().stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }

    @GetMapping("/appointmentSearch")
    public List<AppointmentDto> getAppointmentByParam(@RequestParam(name = "param") String param) {
        System.out.println(":::::::::param::::::: "+param);
        if (Strings.isBlank(param)) {
            return this.appointmentService.getAllAppointments().stream()
                    .map(this::convert)
                    .collect(Collectors.toList());
        }
        return this.appointmentService.getAppointmentByParam("%"+param.toLowerCase()+"%").stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }

    @PostMapping(value = "/appointment")
    public ResponseEntity<AppointmentDto> saveAppointment(@RequestBody AppointmentDto appointmentDto) {

        if (appointmentDto.getId() != null) {
            throw new IllegalArgumentException("To update an existing appointment you should be performing a PUT. To create a Appointment, remove the ID as an ID will be assigned at creation.");
        }

        if (Strings.isBlank(appointmentDto.getAppointmentDescription())) {
            throw new IllegalArgumentException("Appointment description was empty/null.");
        }

        if (appointmentDto.getAppointmentDate() == null) {
            throw new IllegalArgumentException("Appointment date was empty/null.");
        }

        if (appointmentDto.getPatientId() == null) {
            throw new IllegalArgumentException("Patient was empty/null.");
        }

        Appointment createdAppointment = this.appointmentService.saveAppointment(Appointment.aAppointment()
                .id(appointmentDto.getId())
                .appointmentDescription(appointmentDto.getAppointmentDescription())
                .appointmentDate(appointmentDto.getAppointmentDate())
                .patient(Patient.aPatient().id(appointmentDto.getPatientId()).build())
                .status("PENDING")
                .build());

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(convert(createdAppointment));
    }

    @PutMapping(value = "/appointment/{id}")
    public ResponseEntity<AppointmentDto> updateAppointment(@PathVariable("id") long id, @RequestBody AppointmentDto appointmentDto) {

        if (Strings.isBlank(appointmentDto.getAppointmentDescription())) {
            throw new IllegalArgumentException("Appointment description was empty/null.");
        }

        if (appointmentDto.getAppointmentDate() == null) {
            throw new IllegalArgumentException("Appointment date was empty/null.");
        }

        Appointment createdAppointment = this.appointmentService.updateAppointment(Appointment.aAppointment()
                .id(id)
                .appointmentDescription(appointmentDto.getAppointmentDescription())
                .appointmentDate(appointmentDto.getAppointmentDate())
                .build());

        return ResponseEntity.status(HttpStatus.OK)
                .body(convert(createdAppointment));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/appointment/{id}")
    public void deleteAppointment(@PathVariable("id") long id) {
        this.appointmentService.delete(id);
    }

    private AppointmentDto convert(Appointment appointment) {
        return AppointmentDto.aAppointmentDto()
                .id(appointment.getId())
                .appointmentDescription(appointment.getAppointmentDescription())
                .appointmentDate(appointment.getAppointmentDate())
                .patient(PatientDto.aPatientDto()
                        .id(appointment.getPatient().getId())
                        .firstname(appointment.getPatient().getFirstname())
                        .lastname(appointment.getPatient().getLastname())
                        .phoneNumber(appointment.getPatient().getPhoneNumber())
                        .email(appointment.getPatient().getEmail())
                        .address(appointment.getPatient().getAddress()).build())
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
