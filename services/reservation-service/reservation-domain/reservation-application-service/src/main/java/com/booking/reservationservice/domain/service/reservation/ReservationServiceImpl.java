package com.booking.reservationservice.domain.service.reservation;

import com.booking.domain.model.outbox.OutboxStatus;
import com.booking.reservationservice.domain.dto.ReservationDto;
import com.booking.reservationservice.domain.outbox.model.ReservationEventPayload;
import com.booking.reservationservice.domain.outbox.scheduler.ReservationOutboxHelper;
import com.booking.reservationservice.domain.repository.ReservationRepository;
import com.booking.reservationservice.domain.service.reservation.helper.ReservationServiceHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.booking.reservationservice.domain.model.entity.Reservation;
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

        reservationOutboxHelper.saveReservationOutboxMessage(reservationServiceHelper
                .createReservationOutboxMessage(reservationEventPayload, OutboxStatus.STARTED));

        return modelMapper.map(savedReservation, ReservationDto.class);
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