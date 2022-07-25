package com.unisussex.hms;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = UserDto.Builder.class)
public class UserDto {
    private final Long id;
    private final String username;
    private final Role role;
    private final Long roleId;

    public UserDto(Builder builder) {
        this.id = builder.id;
        this.username = builder.username;
        this.role = builder.role;
        this.roleId = builder.roleId;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public Role getRole() {
        return role;
    }

    public Long getRoleId() {
        return roleId;
    }

    public static Builder aUserDto() {
        return new Builder();
    }


    @JsonPOJOBuilder(withPrefix = "")
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static final class Builder {
        private Long id;
        private String username;
        private Role role;
        private Long roleId;

        private Builder() {
        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }
        public Builder username(String username ) {
            this.username = username;
            return this;
        }
        public Builder role(Role role) {
            this.role = role;
            return this;
        }
        public Builder roleId(Long roleId) {
            this.roleId = roleId;
            return this;
        }

        public UserDto build() {
            return new UserDto(this);
        }
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", role=" + role +
                ", roleId=" + roleId +
                '}';
    }
}
