package com.booking.reservationservice.messaging.publisher;

import com.booking.kafka.model.reservation.ReservationEvent;
import com.booking.kafka.service.producer.KafkaProducer;
import com.booking.kafka.service.producer.helper.KafkaMessageHelper;
import com.booking.domain.model.outbox.OutboxStatus;
import com.booking.reservationservice.domain.config.TopicConfig;
import com.booking.reservationservice.domain.outbox.model.ReservationOutboxEvent;
import com.booking.reservationservice.domain.publisher.ReservationEventPublisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.function.BiConsumer;

@Slf4j
@Component
@RequiredArgsConstructor
public class ReservationEventPublisherImpl implements ReservationEventPublisher {

    private final TopicConfig topicConfig;
    private final KafkaProducer<String, ReservationEvent> kafkaProducer;
    private final KafkaMessageHelper kafkaMessageHelper;
    private final ModelMapper modelMapper;

    @Override
    public void publish(ReservationOutboxEvent reservationOutboxEvent,
                        BiConsumer<ReservationOutboxEvent, OutboxStatus> outboxCallback) {
        String reservationId = reservationOutboxEvent.getPayload().getId();

        log.info("Received ReservationOutboxMessage for reservation id: {}", reservationId);
        try {
            ReservationEvent reservationEvent = modelMapper
                    .map(reservationOutboxEvent.getPayload(), ReservationEvent.class);

            kafkaProducer.send(topicConfig.getReservationEventTopicName(),
                    reservationId,
                    reservationEvent,
                    kafkaMessageHelper.getKafkaCallback(topicConfig.getReservationEventTopicName(),
                            reservationEvent,
                            reservationOutboxEvent,
                            outboxCallback,
                            reservationId,
                            "ReservationEvent"));

            log.info("ReservationEvent sent to Kafka for reservation id: {}", reservationId);
        } catch (Exception e) {
            log.error("ReservationMessagePublisher message" +
                    " to kafka with reservation id: {}, error: {}", reservationId, e.getMessage());
        }
    }
}
