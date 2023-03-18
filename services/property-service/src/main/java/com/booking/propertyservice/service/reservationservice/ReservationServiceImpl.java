package com.booking.propertyservice.service.reservationservice;

import com.booking.domain.event.reservation.ReservationEvent;
import com.booking.propertyservice.model.Reservation;
import com.booking.propertyservice.repository.reservationrepository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final ModelMapper modelMapper;

    @Override
    public void createReservation(ReservationEvent reservationEvent) {
        log.info("Create reservation with id {}", reservationEvent.getId());
        Reservation reservation = modelMapper.map(reservationEvent, Reservation.class);
        reservationRepository.save(reservation);
    }

    @Override
    public void deleteReservation(ReservationEvent reservationEvent) {
        log.info("Delete reservation with id {}", reservationEvent.getId());
        Reservation reservation = modelMapper.map(reservationEvent, Reservation.class);
        reservationRepository.delete(reservation);
    }
}
