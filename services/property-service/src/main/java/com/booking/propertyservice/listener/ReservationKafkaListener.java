package com.booking.propertyservice.listener;

import com.booking.domain.event.reservation.ReservationEvent;
import com.booking.kafkaconsumer.service.consumer.KafkaConsumer;
import com.booking.propertyservice.service.reservationservice.ReservationService;
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
public class ReservationKafkaListener implements KafkaConsumer<ReservationEvent> {

    private final ReservationService reservationService;

    @KafkaListener(topics = "${property-service.kafka.reservation-event-topic-name}",
                   groupId = "${kafka-consumer-config.property-consumer-group-id}")
    public void receive(@Payload List<ReservationEvent> messages,
                        @Header(value = KafkaHeaders.RECEIVED_KEY) List<String> keys,
                        @Header(KafkaHeaders.RECEIVED_PARTITION) List<Integer> partitions,
                        @Header(KafkaHeaders.OFFSET) List<Long> offsets) {
        log.info("{} number of reservation created requests received with keys:{}, partitions:{} and offsets: {}",
                messages.size(),
                keys.toString(),
                partitions.toString(),
                offsets.toString());

        messages.forEach(reservationEvent -> {
            switch (reservationEvent.getReservationStatus()) {
                case RESERVED -> reservationService.createReservation(reservationEvent);
                case CANCELED -> reservationService.deleteReservation(reservationEvent);
            }
        });
    }
}
