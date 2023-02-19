package com.booking.reservationservice.domain.outbox.scheduler;

import com.booking.domain.model.outbox.OutboxStatus;
import com.booking.reservationservice.domain.outbox.model.ReservationOutboxEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class ReservationOutboxCleanerScheduler implements OutboxScheduler {

    private final ReservationOutboxHelper reservationOutboxHelper;

    @Override
    @Scheduled(cron = "@midnight")
    public void processOutboxMessage() {
        Optional<List<ReservationOutboxEvent>> outboxMessagesResponse =
                reservationOutboxHelper.getReservationOutboxMessageByOutboxStatus(OutboxStatus.COMPLETED, OutboxStatus.FAILED);

        if (outboxMessagesResponse.isPresent()) {
            List<ReservationOutboxEvent> outboxMessages = outboxMessagesResponse.get();
            log.info("Received {} ReservationOutboxMessage for clean-up. The payloads: {}",
                    outboxMessages.size(),
                    outboxMessages.stream().map(ReservationOutboxEvent::getPayload));
            reservationOutboxHelper.deleteReservationOutboxMessageByOutboxStatus(OutboxStatus.COMPLETED);
        }
    }
}
