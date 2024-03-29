package com.booking.propertyservice.model;


import lombok.*;
import lombok.experimental.Accessors;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Owner {

    private String id;
    private String email;
    private String firstName;
    private String lastName;
    private String image;
}
