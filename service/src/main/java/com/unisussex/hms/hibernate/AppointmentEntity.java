package com.unisussex.hms.hibernate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.unisussex.hms.hibernate.enums.AppointmentStatus;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "appointment")
public class AppointmentEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String appointmentDescription;
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date appointmentDate;
	@ManyToOne
	@JoinColumn(name = "patient", referencedColumnName = "id")
	private PatientEntity patient;

	@Enumerated(EnumType.STRING)
	private AppointmentStatus status;

	public AppointmentEntity() {
	}

	public AppointmentEntity(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAppointmentDescription() {
		return appointmentDescription;
	}

	public void setAppointmentDescription(String appointmentDescription) {
		this.appointmentDescription = appointmentDescription;
	}

	public Date getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(Date appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	public PatientEntity getPatient() {
		return patient;
	}

	public AppointmentEntity setPatient(PatientEntity patient) {
		this.patient = patient;
		return this;
	}

	public AppointmentStatus getStatus() {
		return status;
	}

	public AppointmentEntity setStatus(AppointmentStatus status) {
		this.status = status;
		return this;
	}
}
