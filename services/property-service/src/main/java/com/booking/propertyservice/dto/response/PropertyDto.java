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
import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
@ToString
@Accessors(chain = true)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PropertyDto {
    private String id;
    @NotEmpty
    @Length(min=5, max=100)
    private String title;
    @Valid
    private String propertyType;
    @Valid
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
    private BigDecimal pricePerNight;
    private String country;
    private String image;
    @NotEmpty
    @Length(min=10, max=250)
    private String description;
    @Valid
    private Set<String> amenities;
}
