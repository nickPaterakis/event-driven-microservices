package com.booking.domain.event.user;

import lombok.*;

@Setter
@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserEvent {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String image;
}
