package com.unisussex.hms.hibernate;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "labresult")
public class LabResultEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String diagnosisDescription;
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date diagnosisDate;
	@ManyToOne
	@JoinColumn(name = "patient", referencedColumnName = "id")
	private PatientEntity patient;

	public LabResultEntity() {
	}

	public LabResultEntity(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDiagnosisDescription() {
		return diagnosisDescription;
	}

	public void setDiagnosisDescription(String diagnosisDescription) {
		this.diagnosisDescription = diagnosisDescription;
	}

	public Date getDiagnosisDate() {
		return diagnosisDate;
	}

	public void setDiagnosisDate(Date diagnosisDate) {
		this.diagnosisDate = diagnosisDate;
	}

	public PatientEntity getPatient() {
		return patient;
	}

	public LabResultEntity setPatient(PatientEntity patient) {
		this.patient = patient;
		return this;
	}

}
