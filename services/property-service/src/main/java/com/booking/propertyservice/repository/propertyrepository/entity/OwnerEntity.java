package com.booking.propertyservice.repository.propertyrepository.entity;

import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Document(collection = "owners")
public class OwnerEntity {

    @Id
    private String id;
    private String email;
    private String firstName;
    private String lastName;
    private String image;
}
