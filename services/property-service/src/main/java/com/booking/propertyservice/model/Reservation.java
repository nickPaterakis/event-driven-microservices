package com.booking.propertyservice.model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "reservations")
public class Reservation {

    private String id;
    private String reservationId;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private String propertyId;
    private String country;
}
