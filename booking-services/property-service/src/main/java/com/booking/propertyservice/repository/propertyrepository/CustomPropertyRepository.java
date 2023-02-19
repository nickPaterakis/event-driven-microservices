package com.booking.propertyservice.repository.propertyrepository;

import com.booking.propertyservice.model.Property;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomPropertyRepository {

    Page<Property> findUnreservedProperties(List<String> propertyIds,
                                            Integer guestNumber,
                                            String country,
                                            Pageable pageable);
}
