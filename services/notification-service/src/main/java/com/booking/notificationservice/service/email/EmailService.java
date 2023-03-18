package com.booking.notificationservice.service.email;

import com.booking.domain.event.reservation.ReservationEvent;

import javax.mail.MessagingException;

public interface EmailService {

    void sendReservationToOwner(ReservationEvent reservationEvent);

    void sendCanceledReservationToOwner(ReservationEvent reservationEvent);
}
