package com.booking.reservationservice.messaging.listener;

import com.booking.domain.exception.EntityNotFoundException;
import com.booking.kafka.model.property.PropertyEvent;
import com.booking.kafka.service.consumer.KafkaConsumer;
import com.booking.reservationservice.domain.service.reservation.ReservationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class PropertyDeletedKafkaListener implements KafkaConsumer<PropertyEvent> {

    private final ReservationService reservationService;

    @Override
    @KafkaListener(id = "reservation-topic-consumer",
                  topics = "Booking.Property_Service.Property",
                  properties = {"spring.json.value.default.type=com.booking.kafka.model.property.PropertyEvent"})
    public void receive(@Payload List<PropertyEvent> messages,
                        @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) List<String> keys,
                        @Header(KafkaHeaders.RECEIVED_PARTITION_ID) List<Integer> partitions,
                        @Header(KafkaHeaders.OFFSET) List<Long> offsets) {
        log.info("{} number of property responses received with keys:{}, partitions:{} and offsets: {}",
                messages.size(),
                keys.toString(),
                partitions.toString(),
                offsets.toString());

        messages.forEach(propertyEvent -> {
            try {
                reservationService.deleteReservationsByPropertyId(propertyEvent.getId());
            } catch (EntityNotFoundException e) {
                log.error("No reservation found for property id: {}", propertyEvent.getId());
            }
        });
    }
}
