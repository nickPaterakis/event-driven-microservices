package com.booking.propertyservice.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Set;

@Getter
@Setter
@ToString
@Accessors(chain = true)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PropertyDetailsDto {
    private String id;
    @NotEmpty
    @Length(min=5, max=100)
    private String title;
    @NotEmpty
    private String propertyType;
    @NotEmpty
    private String guestSpace;
    @NotNull
    private Integer maxGuestNumber;
    @NotNull
    private Integer bedroomNumber;
    @NotNull
    private Integer bathNumber;
    @NotNull
    @Digits(integer = 10, fraction = 2)
    @Positive
    private Float pricePerNight;
    @Valid
    private AddressDto address;
    private Set<String> images;
    @NotEmpty
    @Length(min=10, max=250)
    private String description;
    private Set<String> amenities;
    private OwnerDto owner;
}