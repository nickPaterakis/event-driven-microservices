package com.booking.propertyservice.repository.ownerrepository;

import com.booking.propertyservice.model.Owner;

public interface OwnerRepository {

    Owner findById(String ownerId);

    Owner save(Owner owner);
}
