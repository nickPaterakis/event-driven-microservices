package com.booking.propertyservice.repository.reservationrepository;

import com.booking.propertyservice.repository.reservationrepository.entity.ReservationEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ReservationMongoRepository extends MongoRepository<ReservationEntity, String>, CustomReservationRepository {
}
