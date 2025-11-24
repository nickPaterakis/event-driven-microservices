package com.booking.propertyservice.service.ownerservice;

import com.booking.domain.event.user.UserEvent;
import com.booking.domain.exception.EntityNotFoundException;
import com.booking.propertyservice.dto.response.OwnerDto;
import com.booking.propertyservice.model.Owner;
import com.booking.propertyservice.repository.ownerrepository.OwnerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class OwnerServiceImpl implements OwnerService {

    private final OwnerRepository ownerRepository;
    private final ModelMapper modelMapper;

    @Override
    public void updateOwner(UserEvent userEvent) {
        Owner owner = ownerRepository.findById(userEvent.getId());

        log.info("Update owner with email {}", userEvent.getEmail());

        owner.setFirstName(userEvent.getFirstName())
                .setLastName(userEvent.getLastName())
                .setImage(userEvent.getImage());

        ownerRepository.save(owner);
    }

    @Override
    public void saveOwner(OwnerDto ownerDto) {
        try {
            ownerRepository.findById(ownerDto.getId());
        } catch (EntityNotFoundException ex) {
            ownerRepository.save(modelMapper.map(ownerDto, Owner.class));
        }
    }
}
