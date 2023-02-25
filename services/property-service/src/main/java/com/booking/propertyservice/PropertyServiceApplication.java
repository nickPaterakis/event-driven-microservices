package com.booking.propertyservice;

import com.booking.propertyservice.model.Country;
import com.booking.propertyservice.repository.CountryRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;


@EnableMongoRepositories
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
@ComponentScan("com.booking")
public class PropertyServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PropertyServiceApplication.class, args);
	}


	@Bean
	CommandLineRunner init(CountryRepository countryRepository) {
		return args -> {
			ObjectMapper objectMapper = new ObjectMapper();
			TypeReference<List<Country>> typeReference = new TypeReference<>(){};
			InputStream inputStream = TypeReference.class.getResourceAsStream("/data/countries.json");

			try {
				countryRepository.deleteAll();
				List<Country> countries = objectMapper.readValue(inputStream, typeReference);
				countryRepository.saveAll(countries);
			} catch (IOException e) {
				System.out.println("Unable to save countries" + e.getMessage());
			}
		};
	}
}
