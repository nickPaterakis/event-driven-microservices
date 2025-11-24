package com.booking.propertyservice.repository.countryrepository;

import com.booking.propertyservice.repository.countryrepository.entity.CountryEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CountryMongoRepository extends MongoRepository<CountryEntity, String> {
}
