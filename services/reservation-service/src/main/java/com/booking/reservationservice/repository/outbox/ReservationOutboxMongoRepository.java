package com.booking.reservationservice.repository.outbox;

import com.booking.domain.model.outbox.OutboxStatus;
import com.booking.reservationservice.repository.outbox.entity.ReservationOutboxEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
interface ReservationOutboxMongoRepository extends MongoRepository<ReservationOutboxEntity, String> {

    Optional<List<ReservationOutboxEntity>> findByOutboxStatusIn(List<OutboxStatus> outboxStatus);

    void deleteByOutboxStatus(OutboxStatus outboxStatus);
}
