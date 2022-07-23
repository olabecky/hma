package com.unisussex.hms;

public class Patient {
    private final Long id;
    private final String firstname;
    private final String lastname;
    private final String email;


    public Patient(Builder builder) {
        this.id = builder.id;
        this.firstname = builder.firstname;
        this.lastname = builder.lastname;
        this.email = builder.email;
    }

    public Long getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }

    public static Builder aPatient() {
        return new Builder();
    }

    public static final class Builder {
        private Long id;
        private String firstname;
        private String lastname;
        private String email;

        private Builder() {
        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder firstname(String firstname) {
            this.firstname = firstname;
            return this;
        }

        public Builder lastname(String lastname) {
            this.lastname = lastname;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Patient build() {
            return new Patient(this);
        }
    }
}
