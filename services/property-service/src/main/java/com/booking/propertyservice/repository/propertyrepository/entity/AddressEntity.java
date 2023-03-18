package com.booking.propertyservice.repository.propertyrepository.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AddressEntity {

    private String country;
    private String city;
    private String postcode;
    private String streetName;
    private String streetNumber;
}
