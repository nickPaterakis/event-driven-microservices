package com.booking.propertyservice.repository.reservationrepository.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "reservations")
public class ReservationEntity {

    @Id
    private String id;
    private String reservationId;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private String propertyId;
    private String country;
}
