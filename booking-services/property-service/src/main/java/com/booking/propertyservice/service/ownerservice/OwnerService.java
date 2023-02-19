package com.booking.propertyservice.service.ownerservice;

import com.booking.kafka.model.user.UserEvent;

public interface OwnerService {

    void updateOwner(UserEvent userEvent);
}
