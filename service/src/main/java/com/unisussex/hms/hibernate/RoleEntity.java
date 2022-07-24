package com.unisussex.hms.hibernate;

import javax.persistence.*;

@Entity
@Table(name = "ROLE")
public class RoleEntity {

	@Id
	@GeneratedValue(strategy= GenerationType.SEQUENCE, generator="SEQ_ROLE")
	@SequenceGenerator(name = "SEQ_ROLE", sequenceName = "SEQ_ROLE", allocationSize = 1)
	private Long id;
	private String name;
	private String description;

	public RoleEntity() {
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
