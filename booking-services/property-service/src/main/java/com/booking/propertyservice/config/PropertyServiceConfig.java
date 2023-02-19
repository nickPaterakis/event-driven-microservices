package com.booking.propertyservice.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PropertyServiceConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
