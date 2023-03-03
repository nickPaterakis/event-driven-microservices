package com.booking.reservationservice.repository.reservation.entity;

import com.booking.domain.valueobject.Money;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "reservations")
public class ReservationEntity {

    private String id;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private PropertyEntity property;
    private Money price;
    private UserEntity renter;
    private UserEntity owner;
}