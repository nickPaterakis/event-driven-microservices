package com.booking.reservationservice.outbox.model;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserPayload {

    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String image;
}

