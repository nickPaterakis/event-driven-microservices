package com.booking.propertyservice.repository;


import com.booking.propertyservice.model.Country;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CountryRepository extends MongoRepository<Country, String> {

}
