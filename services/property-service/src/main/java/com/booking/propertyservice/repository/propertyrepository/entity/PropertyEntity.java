package com.booking.propertyservice.repository.propertyrepository.entity;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "properties")
public class PropertyEntity {

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
    private AddressEntity address;
    @DBRef
    private OwnerEntity owner;
}