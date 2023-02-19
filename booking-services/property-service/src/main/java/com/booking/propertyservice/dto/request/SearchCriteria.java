package com.booking.propertyservice.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;


@Getter
@Setter
@ToString
@Accessors(chain = true)
public class SearchCriteria {

    @NotEmpty
    private String country;
    @NotNull
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate checkIn;
    @NotNull
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate checkOut;
    @Range(min = 0, max = 16)
    private Integer guestNumber;
    @Min(value = 0)
    private Integer currentPage;




}
