package com.booking.propertyservice.model;


import lombok.*;

import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Property {

    private String id;
    private Integer maxGuestNumber;
    private Integer bedroomNumber;
    private Integer bathNumber;
    private String title;
    private String description;
    private BigDecimal pricePerNight;
    private String propertyType;
    private String guestSpace;
    private Set<String> amenities;
    private Set<String> images;
    private Address address;
    private Owner owner;
}
