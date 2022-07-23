package com.unisussex.hms;

public class Superhero {

	private final Long id;
	private final String name;
	private final String secretIdentity;
	private final Long number;

	private Superhero(Builder builder) {
		this.id = builder.id;
		this.name = builder.name;
		this.secretIdentity = builder.secretIdentity;
		this.number = builder.number;
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

	public Long getNumber() {
		return number;
	}

	public static Builder aSuperhero() {
		return new Builder();
	}

	public static final class Builder {
		private Long id;
		private String name;
		private String secretIdentity;
		private Long number;

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

		public Builder number(Long number) {
			this.number = number;
			return this;
		}

		public Superhero build() {
			return new Superhero(this);
		}
	}
}
