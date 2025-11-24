package com.booking.propertyservice.config;

import com.booking.propertyservice.model.Country;
import com.booking.propertyservice.repository.countryrepository.CountryRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Configuration
public class CountryDataLoaderConfig {

    @Bean
    CommandLineRunner countryDataInitializer(CountryRepository countryRepository) {
        return args -> {
            ObjectMapper objectMapper = new ObjectMapper();
            TypeReference<List<Country>> typeReference = new TypeReference<>() {
            };

            try (InputStream inputStream = TypeReference.class.getResourceAsStream("/data/countries.json")) {
                if (inputStream == null) {
                    throw new IllegalStateException("countries.json not found on classpath");
                }
                countryRepository.deleteAll();
                List<Country> countries = objectMapper.readValue(inputStream, typeReference);
                countryRepository.saveAll(countries);
            } catch (IOException e) {
                System.out.println("Unable to save countries" + e.getMessage());
            }
        };
    }
}
