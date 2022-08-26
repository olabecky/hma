
package com.unisussex.hms;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class User {
    private final Long id;
    private final String username;
    private final Role role;
    private final String password;
    private final boolean mustChangePassword;
    private final String userStatus;
    private final Date lastLoginDate;

    private User(Builder builder) {
        this.id = builder.id;
        this.username = builder.username;
        this.role = builder.role;
        this.password = builder.password;
        this.mustChangePassword = builder.mustChangePassword;
        this.userStatus = builder.userStatus;
        this.lastLoginDate = builder.lastLoginDate;
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

    public String getPassword() {
        return password;
    }

    public boolean isMustChangePassword() {
        return mustChangePassword;
    }
    public String getUserStatus() {
        return userStatus;
    }
    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    public static Builder aUser() {
        return new Builder();
    }

    public static final class Builder {
        private Long id;
        private String username;
        private Role role;
        private String password;
        private boolean mustChangePassword;
        private String userStatus;
        private Date lastLoginDate;

        private Builder() {
        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public Builder password (String password) {
            this.password = password;
            return this;
        }

        public Builder role(Role role) {
            this.role = role;
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

        public Builder lastLoginDate(Date lastLoginDate) {
            this.lastLoginDate = lastLoginDate;
            return this;
        }

        public User build() {
            return new User(this);
        }

    }
}