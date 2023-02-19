package com.booking.handler;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.http.HttpStatus;
import com.fasterxml.jackson.datatype.jsr310.ser.ZonedDateTimeSerializer;
import lombok.Getter;
import lombok.Setter;


import java.time.ZonedDateTime;
import java.util.List;

@Getter
@Setter
public class ErrorResponse {

    private HttpStatus httpStatus;

    private String message;

    private List<String> details;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd@HH:mm:ss.SSSZ")
    @JsonSerialize(using = ZonedDateTimeSerializer.class)
    ZonedDateTime timestamp;

    public ErrorResponse(HttpStatus httpStatus, String message, List<String> details) {
        this.httpStatus = httpStatus;
        this.message = message;
        this.details = details;
        this.timestamp = ZonedDateTime.now();
    }
}
