package com.booking.propertyservice.service.ownerservice;

import com.booking.domain.event.user.UserEvent;
import com.booking.propertyservice.model.Owner;
import com.booking.propertyservice.repository.ownerrepository.OwnerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class OwnerServiceImpl implements OwnerService {

    private final OwnerRepository ownerRepository;

    @Override
    public void updateOwner(UserEvent userEvent) {
        Owner owner = ownerRepository.findById(userEvent.getId());

        log.info("Update owner with email {}", userEvent.getEmail());

        owner.setFirstName(userEvent.getFirstName())
             .setLastName(userEvent.getLastName())
             .setImage(userEvent.getImage());

        ownerRepository.save(owner);
    }
}
