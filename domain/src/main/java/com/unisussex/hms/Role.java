package com.unisussex.hms;

public class Role {

	private final Long id;
	private final String name;
	private final String description;

	private Role(Builder builder) {
		this.id = builder.id;
		this.name = builder.name;
		this.description = builder.description;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}


	public static Builder aRole() {
		return new Builder();
	}

	public static final class Builder {
		private Long id;
		private String name;
		private String description;

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

		public Builder description(String description) {
			this.description = description;
			return this;
		}

		public Role build() {
			return new Role(this);
		}
	}
}
