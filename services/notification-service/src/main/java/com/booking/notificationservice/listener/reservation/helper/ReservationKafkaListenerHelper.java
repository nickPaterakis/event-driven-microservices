package com.booking.notificationservice.listener.reservation.helper;

import com.booking.domain.event.reservation.ReservationEvent;
import com.booking.notificationservice.exception.JsonMappingException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ReservationKafkaListenerHelper {

    private final ObjectMapper objectMapper;

    public ReservationEvent stringToReservationEvent(String jsonReservationEvent) {
        try {
            return objectMapper.readValue(jsonReservationEvent, ReservationEvent.class);
        } catch (JsonProcessingException e) {
            throw new JsonMappingException("Cannot map json to ReservationEvent");
        }
    }
}
