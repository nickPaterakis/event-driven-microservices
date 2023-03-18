package com.booking.reservationservice.service.reservation;

import com.booking.domain.model.outbox.OutboxStatus;
import com.booking.domain.model.reservation.ReservationStatus;
import com.booking.reservationservice.dto.ReservationDto;
import com.booking.reservationservice.model.Reservation;
import com.booking.reservationservice.outbox.model.ReservationEventPayload;
import com.booking.reservationservice.outbox.scheduler.ReservationOutboxHelper;
import com.booking.reservationservice.repository.reservation.ReservationRepository;
import com.booking.reservationservice.service.reservation.helper.ReservationServiceHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private final ReservationServiceHelper reservationServiceHelper;
    private final ReservationOutboxHelper reservationOutboxHelper;
    private final ReservationRepository reservationRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public ReservationDto createReservation(ReservationDto reservationDto) {
        log.info("Create Reservation");
        Reservation reservation = modelMapper.map(reservationDto, Reservation.class);

        Reservation savedReservation = reservationServiceHelper.saveReservation(reservation);

        ReservationEventPayload reservationEventPayload = modelMapper
                .map(savedReservation, ReservationEventPayload.class);

        reservationEventPayload.setReservationStatus(ReservationStatus.RESERVED);

        reservationOutboxHelper.saveReservationOutboxMessage(reservationServiceHelper
                .createReservationOutboxMessage(reservationEventPayload, OutboxStatus.STARTED));

        return modelMapper.map(savedReservation, ReservationDto.class);
    }

    @Override
    public void cancelReservation(String reservationId) {
        log.info("Canceled reservation with id: {}", reservationId);
        Reservation reservation = reservationRepository.findReservationByReservationId(reservationId);

        reservationRepository.deleteReservationByReservationId(reservationId);

        ReservationEventPayload reservationEventPayload = modelMapper
                .map(reservation, ReservationEventPayload.class);

        reservationEventPayload.setReservationStatus(ReservationStatus.CANCELED);

        reservationOutboxHelper.saveReservationOutboxMessage(reservationServiceHelper
                .createReservationOutboxMessage(reservationEventPayload, OutboxStatus.STARTED));
    }

    @Override
    public Set<ReservationDto> getReservationsByRenterId(String renterId) {
        Set<Reservation> reservations =  reservationRepository.findReservationsByRenterId(renterId);

        return reservations.stream()
                .map(reservation -> modelMapper.map(reservation, ReservationDto.class))
                .collect(Collectors.toUnmodifiableSet());
    }

    @Override
    public void deleteReservationsByPropertyId(String propertyId) {
        log.info("Delete reservations for property with property id: {}", propertyId);
        reservationRepository.deleteReservationsByPropertyId(propertyId);
    }
}