package com.booking.propertyservice.repository.countryrepository.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@ToString
@Document(collection = "countries")
public class CountryEntity {

    @Id
    private String id;
    private String code;
    private String name;
}
