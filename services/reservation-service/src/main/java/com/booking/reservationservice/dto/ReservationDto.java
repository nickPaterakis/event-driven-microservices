package com.booking.reservationservice.dto;

import com.booking.domain.valueobject.Money;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.validation.Valid;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReservationDto {

    private String id;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private Money price;
    @Valid
    private PropertyDto property;
    @Valid
    private UserDto renter;
    @Valid
    private UserDto owner;
}
