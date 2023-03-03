package com.booking.propertyservice.service.ownerservice;

import com.booking.domain.event.user.UserEvent;

public interface OwnerService {

    void updateOwner(UserEvent userEvent);
}
