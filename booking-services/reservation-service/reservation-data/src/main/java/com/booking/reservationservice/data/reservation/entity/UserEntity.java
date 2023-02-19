package com.booking.reservationservice.data.reservation.entity;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String image;
}
