package com.booking.propertyservice.service.countryservice;

import com.booking.domain.exception.EntityNotFoundException;
import com.booking.propertyservice.dto.response.CountryDto;
import com.booking.propertyservice.model.Country;
import com.booking.propertyservice.repository.CountryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;

    private final ModelMapper modelMapper;

    @Override
    public List<CountryDto> getCountries() {
        log.info("Get all countries");
        List<Country> countries = countryRepository.findAll();
        if (!countries.isEmpty()) {
            return countries.stream()
                    .map(country -> modelMapper.map(country, CountryDto.class))
                    .collect(Collectors.toList());
        }
        throw new EntityNotFoundException("Countries records don't exist");
    }

}
