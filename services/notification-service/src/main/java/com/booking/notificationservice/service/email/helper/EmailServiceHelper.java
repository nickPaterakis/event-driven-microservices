package com.booking.notificationservice.service.email.helper;

import com.booking.domain.event.reservation.ReservationEvent;
import com.booking.notificationservice.model.Reservation;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

@Slf4j
@Component
@RequiredArgsConstructor
public class EmailServiceHelper {

    private final JavaMailSender emailSender;
    private final SpringTemplateEngine thymeleafTemplateEngine;
    private final ModelMapper modelMapper;

    public void sendEmail(ReservationEvent reservationEvent, String template, String subject) {
        Reservation reservation = modelMapper.map(reservationEvent, Reservation.class);

        Context ctx = getContext(reservation);

        String htmlBody = thymeleafTemplateEngine.process(template, ctx);

        MimeMessage message = emailSender.createMimeMessage();

        MimeMessageHelper helper = null;
        try {
            helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setTo(reservation.getOwner().getEmail());
            helper.setSubject(subject);
            helper.setText(htmlBody, true);
        } catch (MessagingException e) {
            log.error(e.getMessage());
        }

        emailSender.send(message);
    }

    public Context getContext(Reservation reservation) {
        Context ctx = new Context();

        ctx.setVariable("title", reservation.getProperty().getTitle());
        ctx.setVariable("firstName", reservation.getRenter().getFirstName());
        ctx.setVariable("lastName", reservation.getRenter().getLastName());
        ctx.setVariable("email", reservation.getRenter().getEmail());
        ctx.setVariable("checkIn", reservation.getCheckIn());
        ctx.setVariable("checkOut", reservation.getCheckOut());
        ctx.setVariable("price", reservation.getTotalPrice().getAmount());
        ctx.setVariable("propertyType",reservation.getProperty().getPropertyType());
        ctx.setVariable("location", reservation.getProperty().getLocation());
        ctx.setVariable("pricePerNight", reservation.getProperty().getPricePerNight().getAmount());

        return ctx;
    }
}
