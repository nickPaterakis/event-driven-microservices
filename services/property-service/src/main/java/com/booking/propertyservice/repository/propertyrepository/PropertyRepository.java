package com.booking.propertyservice.repository.propertyrepository;

import com.booking.propertyservice.model.Property;
import com.booking.propertyservice.model.PropertyPage;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PropertyRepository {

    Property findPropertyByPropertyId(String propertyId);

    List<Property> findPropertiesByOwnerId(String ownerId);

    PropertyPage findUnreservedProperties(List<String> reservedPropertiesIds,
                                          Integer guestNumber,
                                          String country,
                                          Pageable pageable);

    void deletePropertyByPropertyId(String propertyId);

    Property saveProperty(Property property);

}
