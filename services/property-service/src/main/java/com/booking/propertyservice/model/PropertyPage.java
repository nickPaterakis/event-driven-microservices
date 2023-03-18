package com.booking.propertyservice.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PropertyPage {

    private Long totalElements;
    private List<Property> properties;
}