package com.booking.propertyservice.model;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    private String country;
    private String city;
    private String postcode;
    private String streetName;
    private String streetNumber;
}
