package com.booking.reservationservice.domain.model.entity;

import com.booking.domain.model.AggregateRoot;
import com.booking.domain.valueobject.Money;

import java.time.LocalDate;

public class Reservation extends AggregateRoot<String> {
    private LocalDate checkIn;
    private LocalDate checkOut;
    private Property property;
    private Money price;
    private User renter;
    private User owner;

    public Reservation(LocalDate checkIn, LocalDate checkOut, Property property, Money price, User renter, User owner) {
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.property = property;
        this.price = price;
        this.renter = renter;
        this.owner = owner;
    }

    public Reservation() {
    }

    private Reservation(Builder builder) {
        super.setId(builder.id);
        checkIn = builder.checkIn;
        checkOut = builder.checkOut;
        property = builder.property;
        price = builder.price;
        renter = builder.renter;
        owner = builder.owner;
    }

    public LocalDate getCheckIn() {
        return checkIn;
    }

    public LocalDate getCheckOut() {
        return checkOut;
    }

    public Property getProperty() {
        return property;
    }

    public Money getPrice() {
        return price;
    }

    public User getRenter() {
        return renter;
    }

    public User getOwner() {
        return owner;
    }

    public void setCheckIn(LocalDate checkIn) {
        this.checkIn = checkIn;
    }

    public void setCheckOut(LocalDate checkOut) {
        this.checkOut = checkOut;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public void setPrice(Money price) {
        this.price = price;
    }

    public void setRenter(User renter) {
        this.renter = renter;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public static Builder builder() {
        return new Builder();
    }
    public static final class Builder {
        private String id;
        private LocalDate checkIn;
        private LocalDate checkOut;
        private Property property;
        private Money price;
        private User renter;
        private User owner;

        private Builder() {
        }

        public Builder id(String val) {
            id = val;
            return this;
        }

        public Builder checkIn(LocalDate val) {
            checkIn = val;
            return this;
        }

        public Builder checkOut(LocalDate val) {
            checkOut = val;
            return this;
        }

        public Builder property(Property val) {
            property = val;
            return this;
        }

        public Builder price(Money val) {
            price = val;
            return this;
        }

        public Builder renter(User val) {
            renter = val;
            return this;
        }

        public Builder owner(User val) {
            owner = val;
            return this;
        }

        public Reservation build() {
            return new Reservation(this);
        }
    }
}
