package com.booking.reservationservice.controller;

import com.booking.domain.exception.EntityNotFoundException;
import com.booking.reservationservice.dto.ReservationDto;
import com.booking.reservationservice.service.reservation.ReservationService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping(value = "api/v1/reservations", produces = "application/json")
public class ReservationController {

    private final ReservationService reservationService;

    @Operation(summary = "Create Reservation")
    @ApiResponse(code = 201, message = "Reservation created", response = ReservationDto.class)
    @PostMapping
    public ResponseEntity<ReservationDto> createReservation(@Valid @RequestBody ReservationDto reservationDto) {
        ReservationDto createdReservation = reservationService.createReservation(reservationDto);
        return  ResponseEntity.status(HttpStatus.CREATED).body(createdReservation);
    }

    @Operation(summary = "Cancel Reservation")
    @ApiResponse(code = 200, message = "Reservation canceled", response = ReservationDto.class)
    @DeleteMapping("/{reservationId}")
    public ResponseEntity<String> canceledReservation(@NotEmpty @PathVariable String reservationId) {
        reservationService.cancelReservation(reservationId);
        return ResponseEntity.status(HttpStatus.OK).body("Canceled reservation with id:" + reservationId);
    }

    @Operation(summary = "Find reservations by renter's id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Reservations found", response = ReservationDto.class),
            @ApiResponse(code = 404, message = "Reservations don't found ", response = EntityNotFoundException.class)
    })
    @GetMapping("/renter/{renterId}")
    public ResponseEntity<Set<ReservationDto>> getReservationByRenterId(@NotEmpty @PathVariable String renterId) {
        Set<ReservationDto> reservations = reservationService.getReservationsByRenterId(renterId);
        return ResponseEntity.status(HttpStatus.OK).body(reservations);
    }
}

