package com.booking.notificationservice.listener.reservation;

import com.booking.notificationservice.listener.reservation.helper.ReservationKafkaListenerHelper;
import com.booking.notificationservice.service.email.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ReservationKafkaListener {

    private final EmailService emailService;
    private final ReservationKafkaListenerHelper reservationKafkaListenerHelper;

    @KafkaListener(topics = "${notification-service.kafka.reservation-event-topic-name}",
            groupId = "${kafka-consumer-config.notification-consumer-group-id}")
    public void receive(@Payload String message,
                        @Header(value = KafkaHeaders.RECEIVED_KEY) String key,
                        @Header(KafkaHeaders.RECEIVED_PARTITION) Integer partition,
                        @Header(KafkaHeaders.OFFSET) Long offset) {
        log.info("Reservation created request received with key:{}, partition:{} and offset: {}",
                key,
                partition,
                offset);

        emailService.sendReservationToOwner(reservationKafkaListenerHelper.stringToReservationEvent(message));
    }
}

