package com.booking.propertyservice.service.reservationservice;

import com.booking.kafka.model.reservation.ReservationEvent;
import com.booking.propertyservice.dto.mapper.ReservationMapper;
import com.booking.propertyservice.model.Reservation;
import com.booking.propertyservice.repository.reservationrepository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;

    @Override
    public void createReservation(ReservationEvent reservationEvent) {
        log.info("Create reservation with id {}", reservationEvent.getId());
        Reservation reservation = ReservationMapper.reservationToReservationEvent(reservationEvent);
        reservationRepository.save(reservation);
    }
}
