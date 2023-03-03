package com.booking.reservationservice.repository.reservation;

import com.booking.domain.exception.EntityNotFoundException;
import com.booking.reservationservice.model.Reservation;
import com.booking.reservationservice.repository.reservation.entity.ReservationEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ReservationRepositoryImpl implements ReservationRepository {

    private final ReservationMongoRepository reservationMongoRepository;
    private final ModelMapper modelMapper;
    @Override
    public Reservation save(Reservation reservation) {
        ReservationEntity reservationEntity = reservationMongoRepository
                .save(modelMapper.map(reservation, ReservationEntity.class));
        return modelMapper.map(reservationEntity, Reservation.class);
    }

    @Override
    public Set<Reservation> findReservationsByRenterId(String renterId) {
        Set<ReservationEntity> reservations = reservationMongoRepository.findReservationEntitiesByRenterId(renterId)
                .orElseThrow(() -> new EntityNotFoundException("Reservations don't found for renter with id: " + renterId));

        return reservations.stream()
                .map(reservation -> modelMapper.map(reservation, Reservation.class))
                .collect(Collectors.toUnmodifiableSet());
    }

    @Override
    public void deleteReservationsByPropertyId(String propertyId) {
        Set<ReservationEntity> reservationEntities =
                reservationMongoRepository.findReservationEntitiesByPropertyId(propertyId);

        if (reservationEntities.isEmpty()) {
            throw new EntityNotFoundException("Reservations don't found with property id: " + propertyId);
        }

        reservationMongoRepository.deleteByPropertyId(propertyId);
    }
}
