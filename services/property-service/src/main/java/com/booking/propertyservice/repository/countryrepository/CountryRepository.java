package com.booking.propertyservice.repository.countryrepository;


import com.booking.propertyservice.model.Country;

import java.util.List;

public interface CountryRepository {

    List<Country> findAll();

    void deleteAll();

    void saveAll(List<Country> countries);
}
