package com.booking.propertyservice.repository.propertyrepository;

import com.booking.propertyservice.model.Property;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CustomPropertyRepositoryImpl implements CustomPropertyRepository {

    private final MongoTemplate mongoTemplate;

    public Page<Property> findUnreservedProperties(List<String> propertyIds,
                                                   Integer guestNumber,
                                                   String country,
                                                   Pageable pageable) {
        final Query query = new Query().with(pageable);
        final List<Criteria> criteria = new ArrayList<>();

        if (!propertyIds.isEmpty()) {
            criteria.add(Criteria.where("_id").nin(propertyIds));
        }

        criteria.add(Criteria.where("maxGuestNumber").gte(guestNumber));

        criteria.add(Criteria.where("address.country").is(country));

        query.addCriteria(new Criteria().andOperator(criteria.toArray(new Criteria[0])));

        return PageableExecutionUtils.getPage(
                mongoTemplate.find(query, Property.class),
                pageable,
                () -> mongoTemplate.count(query.skip(0).limit(0), Property.class));
    }
}
