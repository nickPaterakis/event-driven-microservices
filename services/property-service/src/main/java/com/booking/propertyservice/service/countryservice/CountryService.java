package com.booking.propertyservice.service.countryservice;

import com.booking.propertyservice.dto.response.CountryDto;

import java.util.List;

public interface CountryService {

    List<CountryDto> getCountries();
}
