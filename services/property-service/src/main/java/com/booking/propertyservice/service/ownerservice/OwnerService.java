package com.booking.propertyservice.service.ownerservice;

import com.booking.domain.event.user.UserEvent;
import com.booking.propertyservice.dto.response.OwnerDto;

public interface OwnerService {

    void updateOwner(UserEvent userEvent);

    void saveOwner(OwnerDto ownerDto);
}
