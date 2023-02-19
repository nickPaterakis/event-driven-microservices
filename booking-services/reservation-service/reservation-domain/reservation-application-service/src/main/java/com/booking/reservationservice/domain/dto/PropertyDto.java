package com.booking.reservationservice.domain.dto;

import com.booking.domain.valueobject.Money;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PropertyDto {

    @NotEmpty
    private String id;
    @NotEmpty
    private String title;
    private String propertyType;
    private Money pricePerNight;
    @NotEmpty
    private String country;
    @NotEmpty
    private String location;
}
