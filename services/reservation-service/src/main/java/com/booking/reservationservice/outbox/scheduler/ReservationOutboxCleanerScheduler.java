package com.booking.reservationservice.outbox.scheduler;

import com.booking.domain.model.outbox.OutboxStatus;
import com.booking.reservationservice.outbox.model.ReservationOutboxEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class ReservationOutboxCleanerScheduler implements OutboxScheduler {

    private final ReservationOutboxHelper reservationOutboxHelper;

    @Override
    @Scheduled(cron = "@midnight")
    public void processOutboxMessage() {
        List<ReservationOutboxEvent> outboxMessagesResponse =
                reservationOutboxHelper.getReservationOutboxMessageByOutboxStatus(OutboxStatus.COMPLETED, OutboxStatus.FAILED);

        if (!outboxMessagesResponse.isEmpty()) {
            log.info("Received {} ReservationOutboxMessage for clean-up. The payloads: {}",
                    outboxMessagesResponse.size(),
                    outboxMessagesResponse.stream().map(ReservationOutboxEvent::getPayload));
            reservationOutboxHelper.deleteReservationOutboxMessageByOutboxStatus(OutboxStatus.COMPLETED);
        }
    }
}
