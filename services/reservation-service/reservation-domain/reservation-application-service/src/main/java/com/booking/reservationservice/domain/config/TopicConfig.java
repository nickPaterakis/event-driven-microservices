package com.booking.reservationservice.domain.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "reservation-service.kafka")
public class TopicConfig {

    private String reservationEventTopicName;
}
