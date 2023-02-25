package com.booking.userservice.data.user.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Getter
@Setter
@ToString
@Builder
@Accessors(chain = true)
@Document(collection = "users")
public class UserEntity {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String image;
}
