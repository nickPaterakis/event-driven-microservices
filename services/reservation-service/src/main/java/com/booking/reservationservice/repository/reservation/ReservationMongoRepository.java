package com.booking.reservationservice.repository.reservation;

import com.booking.reservationservice.repository.reservation.entity.ReservationEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface ReservationMongoRepository extends MongoRepository<ReservationEntity, String> {

    Optional<Set<ReservationEntity>> findReservationEntitiesByRenterId(String renterId);

    Set<ReservationEntity> findReservationEntitiesByPropertyId(String propertyId);

    void deleteByPropertyId(String propertyId);
}
