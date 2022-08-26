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
    private final String password;
    private final String repeatPassword;
    private final boolean mustChangePassword;
    private final String userStatus;

    public UserDto(Builder builder) {
        this.id = builder.id;
        this.username = builder.username;
        this.role = builder.role;
        this.roleId = builder.roleId;
        this.password = builder.password;
        this.repeatPassword = builder.repeatPassword;
        this.mustChangePassword = builder.mustChangePassword;
        this.userStatus = builder.userStatus;
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
    public String getPassword() {
        return password;
    }
    public String getRepeatPassword() {
        return repeatPassword;
    }
    public boolean isMustChangePassword() {
        return mustChangePassword;
    }

    public String getUserStatus() {
        return userStatus;
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

        private String password;

        private String repeatPassword;

        private boolean mustChangePassword;
        private String userStatus;

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

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder repeatPassword(String repeatPassword) {
            this.repeatPassword = repeatPassword;
            return this;
        }

        public Builder mustChangePassword(boolean mustChangePassword) {
            this.mustChangePassword = mustChangePassword;
            return this;
        }

        public Builder userStatus(String userStatus) {
            this.userStatus = userStatus;
            return this;
        }

        public UserDto build() {
            return new UserDto(this);
        }
    }


}
