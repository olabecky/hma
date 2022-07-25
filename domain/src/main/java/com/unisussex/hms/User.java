
package com.unisussex.hms;


public class User {
    private final Long id;
    private final String username;
    private final String role;

    private User(Builder builder) {
        this.id = builder.id;
        this.username = builder.username;
        this.role = builder.role;
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

    public static Builder aUser() {
        return new Builder();
    }

    public static final class Builder {
        private Long id;
        private String username;
        private String role;

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

        public Builder role(String role) {
            this.role = role;
            return this;
        }

        public User build() {
            return new User(this);
        }

    }
}