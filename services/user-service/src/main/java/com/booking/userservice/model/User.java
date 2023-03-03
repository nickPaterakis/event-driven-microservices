package com.booking.userservice.model;

import com.booking.domain.model.AggregateRoot;
import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
public class User extends AggregateRoot<String> {

    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String image;

    @Builder
    public User(String id, String firstName, String lastName, String email, String phone, String image) {
        super.setId(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.image = image;
    }
}
