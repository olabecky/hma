package com.unisussex.hms;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

public class UserDto {
    private final Long id;
    private final String username;
    private final String role;

    public UserDto(Builder builder) {
        this.id = builder.id;
        this.username = builder.username;
        this.role = builder.role;
    }

    public static UserDto.Builder aPatientDto() {
        return new UserDto.Builder();
    }
    @JsonPOJOBuilder(withPrefix = "")
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static final class Builder {
        private Long id;
        private String username;
        private String role;

        private Builder() {
        }

        public UserDto.Builder id(Long id) {
            this.id = id;
            return this;
        }
        public UserDto.Builder username(String username ) {
            this.username = username;
            return this;
        }
        public UserDto.Builder role(String role) {
            this.role = role;
            return this;
        }

        public UserDto build() {
            return new UserDto(this);
        }
    }

    public static UserDto.Builder aUserDto() {
        return new UserDto.Builder();
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getRole() {
        return role;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
