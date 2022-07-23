package com.unisussex.hms.hibernate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity(name = "SUPERHERO")
public class SuperheroEntity {

	@Id
	@GeneratedValue(strategy= GenerationType.SEQUENCE, generator="SEQ_SUPERHERO")
	@SequenceGenerator(name = "SEQ_SUPERHERO", sequenceName = "SEQ_SUPERHERO", allocationSize = 1)
	private Long id;
	private String name;
	private String secretIdentity;
	private Long number;

	public SuperheroEntity() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSecretIdentity() {
		return secretIdentity;
	}

	public void setSecretIdentity(String secretIdentity) {
		this.secretIdentity = secretIdentity;
	}

	public Long getNumber() {
		return number;
	}

	public void setNumber(Long number) {
		this.number = number;
	}
}
