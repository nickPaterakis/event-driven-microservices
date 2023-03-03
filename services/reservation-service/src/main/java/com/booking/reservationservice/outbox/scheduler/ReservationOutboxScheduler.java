package com.booking.reservationservice.outbox.scheduler;

import com.booking.domain.model.outbox.OutboxStatus;
import com.booking.reservationservice.outbox.model.ReservationOutboxEvent;
import com.booking.reservationservice.publisher.ReservationEventPublisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class ReservationOutboxScheduler implements OutboxScheduler {

    private final ReservationOutboxHelper reservationOutboxHelper;
    private final ReservationEventPublisher reservationEventPublisher;

    @Override
    @Transactional
    @Scheduled(fixedDelayString = "${reservation-service.outbox.outbox-scheduler-fixed-rate}",
            initialDelayString = "${reservation-service.outbox.outbox-scheduler-initial-delay}")
    public void processOutboxMessage() {
        List<ReservationOutboxEvent> outboxMessagesResponse =
                reservationOutboxHelper.getReservationOutboxMessageByOutboxStatus(OutboxStatus.STARTED);

        if (!outboxMessagesResponse.isEmpty()) {
            log.info("Received {} ReservationOutboxMessage with ids: {}, sending to message bus!",
                    outboxMessagesResponse.size(),
                    outboxMessagesResponse.stream().map(ReservationOutboxEvent::getId)
                            .collect(Collectors.joining(",")));
            outboxMessagesResponse.forEach(outboxMessage ->
                    reservationEventPublisher.publish(outboxMessage, this::updateOutboxStatus));
            log.info("{} ReservationOutboxMessage sent to message bus!", outboxMessagesResponse.size());
        }
    }

    private void updateOutboxStatus(ReservationOutboxEvent reservationOutboxEvent, OutboxStatus outboxStatus) {
        reservationOutboxEvent.setOutboxStatus(outboxStatus);
        reservationOutboxHelper.saveReservationOutboxMessage(reservationOutboxEvent);
        log.info("ReservationOutboxMessage is updated with outbox status: {}", outboxStatus.name());
    }
}
