package com.booking.propertyservice.repository.reservationrepository;

import com.booking.propertyservice.model.Reservation;
import com.booking.propertyservice.repository.reservationrepository.entity.ReservationEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ReservationRepositoryImpl implements ReservationRepository {
    private final ReservationMongoRepository reservationMongoRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<String> findReservedPropertiesIds(String country, LocalDate checkIn, LocalDate checkOut) {
        return reservationMongoRepository.findReservedPropertiesIds(country, checkIn, checkOut);
    }

    @Override
    public Reservation save(Reservation reservation) {
        ReservationEntity reservationEntity = modelMapper.map(reservation, ReservationEntity.class);
        ReservationEntity savedReservationEntity = reservationMongoRepository.save(reservationEntity);
        return modelMapper.map(savedReservationEntity, Reservation.class);
    }

    @Override
    public void delete(Reservation reservation) {
        ReservationEntity reservationEntity = modelMapper.map(reservation, ReservationEntity.class);
        reservationMongoRepository.delete(reservationEntity);
    }
}
