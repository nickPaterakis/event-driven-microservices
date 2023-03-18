package com.booking.notificationservice.service.email;

import com.booking.domain.event.reservation.ReservationEvent;
import com.booking.notificationservice.service.email.helper.EmailServiceHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;

@Slf4j
@Component
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final EmailServiceHelper emailServiceHelper;

    @Override
    public void sendReservationToOwner(ReservationEvent reservationEvent) {
        log.info("Sending reservation email to {}", reservationEvent.getOwner().getEmail());

        String template = "email-new-reservation.html";
        String subject = "New Reservation at " + reservationEvent.getProperty().getTitle();

        emailServiceHelper.sendEmail(reservationEvent, template, subject);
    }

    @Override
    public void sendCanceledReservationToOwner(ReservationEvent reservationEvent) {
        log.info("Sending canceled reservation email to {}", reservationEvent.getOwner().getEmail());

        String template = "email-cancellation.html";
        String subject = "Cancellation from " + reservationEvent.getRenter().getFirstName()
                + " " + reservationEvent.getRenter().getLastName();

        emailServiceHelper.sendEmail(reservationEvent, template, subject);
    }
}
