package com.unisussex.hms;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = SuperHeroDto.Builder.class)
public class SuperHeroDto {

	private final Long id;
	private final String name;
	private final String secretIdentity;
	private final String newField = "this is a test";

	private SuperHeroDto(Builder builder) {
		this.id = builder.id;
		this.name = builder.name;
		this.secretIdentity = builder.secretIdentity;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getSecretIdentity() {
		return secretIdentity;
	}

	public String getNewField() {
		return newField;
	}

	public static Builder aSuperHeroDto() {
		return new Builder();
	}

	@JsonPOJOBuilder(withPrefix = "")
	@JsonIgnoreProperties(ignoreUnknown = true)
	public static final class Builder {
		private Long id;
		private String name;
		private String secretIdentity;

		private Builder() {
		}

		public Builder id(Long id) {
			this.id = id;
			return this;
		}

		public Builder name(String name) {
			this.name = name;
			return this;
		}

		public Builder secretIdentity(String secretIdentity) {
			this.secretIdentity = secretIdentity;
			return this;
		}

		public SuperHeroDto build() {
			return new SuperHeroDto(this);
		}
	}
}
