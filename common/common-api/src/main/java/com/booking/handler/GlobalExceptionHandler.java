package com.booking.handler;

import com.booking.domain.exception.EntityNotFoundException;
import com.booking.domain.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handleNotFoundExceptions(NotFoundException notFoundException) {
        return createHttpErrorInfo(HttpStatus.NOT_FOUND, notFoundException);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> serverExceptionHandler(Exception ex) {
        return createHttpErrorInfo(HttpStatus.INTERNAL_SERVER_ERROR, ex);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex) {
        return createHttpErrorInfo(HttpStatus.BAD_REQUEST, ex);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException ex) {
        return createHttpErrorInfo(HttpStatus.NOT_FOUND, ex);
    }

    private ResponseEntity<Object> createHttpErrorInfo(HttpStatus httpStatus, Exception ex) {
        String message = ex.getMessage();
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());

        ErrorResponse errorResponse = new ErrorResponse(httpStatus, message, details);

        log.error("Returning HTTP status: {} for message: {}", httpStatus, ex);
        return buildResponseEntity(errorResponse);
    }

    private ResponseEntity<Object> buildResponseEntity(ErrorResponse errorResponse) {
        return new ResponseEntity<>(errorResponse, errorResponse.getHttpStatus());
    }
}