package com.booking.propertyservice.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
public class Address {

    private String country;
    private String city;
    private String postcode;
    private String streetName;
    private String streetNumber;
}
