package com.booking.propertyservice.repository.reservationrepository;

import com.booking.propertyservice.model.Reservation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
public class CustomReservationRepositoryImpl implements CustomReservationRepository {

    private final MongoTemplate mongoTemplate;

    private static final String CHECK_IN = "checkIn";
    private static final String CHECK_OUT = "checkOut";
    private static final String COUNTRY = "country";
    private static final String RESERVATIONS_COLLECTION = "reservations";

    @Override
    public List<String> findReservedPropertiesIds(String country, LocalDate checkIn, LocalDate checkOut) {
        Query query = new Query();
        query.addCriteria(Criteria.where(COUNTRY).is(country));
        query.addCriteria(overlappingStayCriteria(checkIn, checkOut));

        return mongoTemplate.find(query, Reservation.class, RESERVATIONS_COLLECTION)
                .stream()
                .map(Reservation::getPropertyId)
                .distinct()
                .toList();
    }

    private Criteria overlappingStayCriteria(LocalDate checkIn, LocalDate checkOut) {
        List<Criteria> overlapScenarios = List.of(
                andCriteria(Criteria.where(CHECK_IN).lte(checkIn), Criteria.where(CHECK_OUT).gte(checkIn)),
                andCriteria(Criteria.where(CHECK_IN).gte(checkIn), Criteria.where(CHECK_IN).lte(checkOut)),
                andCriteria(Criteria.where(CHECK_IN).gte(checkIn), Criteria.where(CHECK_OUT).lte(checkOut)),
                andCriteria(Criteria.where(CHECK_IN).lte(checkIn), Criteria.where(CHECK_OUT).gte(checkOut))
        );

        return new Criteria().orOperator(overlapScenarios.toArray(new Criteria[0]));
    }

    private Criteria andCriteria(Criteria... criteria) {
        return new Criteria().andOperator(criteria);
    }
}
