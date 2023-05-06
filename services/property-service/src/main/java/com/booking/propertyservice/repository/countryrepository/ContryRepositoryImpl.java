package com.booking.propertyservice.repository.countryrepository;

import com.booking.propertyservice.model.Country;
import com.booking.propertyservice.repository.countryrepository.entity.CountryEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ContryRepositoryImpl implements CountryRepository {
    private final CountryMongoRepository countryMongoRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<Country> findAll() {
        List<CountryEntity> countryEntities = countryMongoRepository.findAll();
        return countryEntities.stream()
                .map(countryEntity -> modelMapper.map(countryEntity, Country.class))
                .toList();
    }
}
