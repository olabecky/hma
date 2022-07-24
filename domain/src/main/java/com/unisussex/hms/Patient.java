package com.unisussex.hms;

public class Patient {
    private final Long id;
    private final String firstname;
    private final String lastname;
    private final String email;

    private final String address;
    private final String phoneNumber;


    public Patient(Builder builder) {
        this.id = builder.id;
        this.firstname = builder.firstname;
        this.lastname = builder.lastname;
        this.email = builder.email;
        this.address = builder.address;
        this.phoneNumber = builder.phoneNumber;
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

    public String getAddress(){return address;};

    public String getPhoneNumber(){return phoneNumber;}

    public static Builder aPatient() {
        return new Builder();
    }

    public static final class Builder {
        private Long id;
        private String firstname;
        private String lastname;
        private String email;
        private String address;
        private String phoneNumber;

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

        public Builder address(String address) {
            this.address = address;
            return this;
        }

        public Builder phoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }


        public Patient build() {
            return new Patient(this);
        }
    }
}
