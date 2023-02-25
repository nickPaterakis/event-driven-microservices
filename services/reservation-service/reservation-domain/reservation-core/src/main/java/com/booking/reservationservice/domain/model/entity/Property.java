package com.booking.reservationservice.domain.model.entity;

import com.booking.domain.model.BaseEntity;
import com.booking.domain.valueobject.Money;

public class Property extends BaseEntity<String> {

    private String title;
    private String propertyType;
    private String country;
    private String location;
    private Money pricePerNight;

    public Property(String title, String propertyType, String country, String location, Money pricePerNight) {
        this.title = title;
        this.propertyType = propertyType;
        this.country = country;
        this.location = location;
        this.pricePerNight = pricePerNight;
    }

    public Property() {
    }

    private Property(Builder builder) {
        super.setId(builder.id);
        title = builder.title;
        propertyType = builder.propertyType;
        country = builder.country;
        location = builder.location;
        pricePerNight = builder.pricePerNight;
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getTitle() {
        return title;
    }

    public String getPropertyType() {
        return propertyType;
    }

    public String getCountry() {
        return country;
    }

    public String getLocation() {
        return location;
    }

    public Money getPricePerNight() {
        return pricePerNight;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setPricePerNight(Money pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public static final class Builder {
        private String id;
        private String title;
        private String propertyType;
        private String country;
        private String location;
        private Money pricePerNight;

        private Builder() {
        }

        public Builder id(String val) {
            id = val;
            return this;
        }

        public Builder title(String val) {
            title = val;
            return this;
        }

        public Builder propertyType(String val) {
            propertyType = val;
            return this;
        }

        public Builder country(String val) {
            country = val;
            return this;
        }

        public Builder location(String val) {
            location = val;
            return this;
        }

        public Builder pricePerNight(Money val) {
            pricePerNight = val;
            return this;
        }

        public Property build() {
            return new Property(this);
        }
    }
}
