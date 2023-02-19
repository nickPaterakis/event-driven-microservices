package com.booking.reservationservice.domain.model.entity;

import com.booking.domain.model.BaseEntity;

public class User extends BaseEntity<String> {

    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String image;

    public User(String firstName, String lastName, String email, String phone, String image) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.image = image;
    }

    public User() {

    }

    private User(Builder builder) {
        super.setId(builder.id);
        firstName = builder.firstName;
        lastName = builder.lastName;
        email = builder.email;
        phone = builder.phone;
        image = builder.image;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() { return phone; }

    public String getImage() {
        return image;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public static final class Builder {
        private String id;
        private String firstName;
        private String lastName;
        private String email;
        private String phone;
        private String image;

        private Builder() {
        }

        public Builder id(String val) {
            id = val;
            return this;
        }

        public Builder firstName(String val) {
            firstName = val;
            return this;
        }

        public Builder lastName(String val) {
            lastName = val;
            return this;
        }

        public Builder email(String val) {
            email = val;
            return this;
        }

        public Builder phone(String val) {
            phone = val;
            return this;
        }

        public Builder image(String val) {
            image = val;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}
