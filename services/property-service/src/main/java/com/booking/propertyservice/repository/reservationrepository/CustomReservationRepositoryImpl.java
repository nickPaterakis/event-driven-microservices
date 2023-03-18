package com.booking.propertyservice.repository.reservationrepository;

import com.booking.propertyservice.model.Reservation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class CustomReservationRepositoryImpl implements CustomReservationRepository {

    private final MongoTemplate mongoTemplate;

    private static final String CHECK_IN = "checkIn";
    private static final String CHECK_OUT = "checkOut";
    private static final String COUNTRY = "country";

    @Override
    public List<String> findReservedPropertiesIds(String country, LocalDate checkIn, LocalDate checkOut) {
        final Query query = new Query();
        final List<Criteria> criteria = new ArrayList<>();

        criteria.add(new Criteria().andOperator(
                Criteria.where(CHECK_IN).lte(checkIn),
                Criteria.where(CHECK_OUT).gte(checkIn)
        ));

        criteria.add(new Criteria().andOperator(
                Criteria.where(CHECK_IN).gte(checkIn),
                Criteria.where(CHECK_IN).lte(checkOut)
        ));

        criteria.add(new Criteria().andOperator(
                Criteria.where(CHECK_IN).gte(checkIn),
                Criteria.where(CHECK_OUT).lte(checkOut)
        ));

        criteria.add(new Criteria().andOperator(
                Criteria.where(CHECK_IN).lte(checkIn),
                Criteria.where(CHECK_OUT).gte(checkOut)
        ));

        query.addCriteria(new Criteria().andOperator(
                Criteria.where(COUNTRY).is(country),
                new Criteria().orOperator(criteria.toArray(new Criteria[0]))));

        List<Reservation> reservations = mongoTemplate.find(query, Reservation.class, "reservations");
        
        return reservations.stream()
                .map(Reservation::getPropertyId)
                .toList();
    }
}
