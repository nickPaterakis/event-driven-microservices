package com.booking.userservice.domain.model.entity;

import com.booking.domain.model.AggregateRoot;

public class User extends AggregateRoot<String> {
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String phone;
    private final String image;

    private User(Builder builder) {
        super.setId(builder.id);
        firstName = builder.firstName;
        lastName = builder.lastName;
        phone = builder.phone;
        email = builder.email;
        image = builder.image;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getImage() {
        return image;
    }

    public static Builder builder() {
        return new Builder();
    }
    public static final class Builder {
        private String id;
        private String firstName;
        private String lastName;
        private String phone;
        private String email;
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

        public Builder phone(String val) {
            phone = val;
            return this;
        }

        public Builder email(String val) {
            email = val;
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
