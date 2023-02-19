package com.booking.propertyservice.repository.reservationrepository;

import com.booking.propertyservice.model.Reservation;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ReservationRepository extends MongoRepository<Reservation, String>, CustomReservationRepository {
}
