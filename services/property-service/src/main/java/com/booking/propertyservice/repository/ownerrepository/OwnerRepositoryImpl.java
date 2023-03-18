package com.booking.propertyservice.repository.ownerrepository;

import com.booking.domain.exception.EntityNotFoundException;
import com.booking.propertyservice.model.Owner;
import com.booking.propertyservice.repository.propertyrepository.entity.OwnerEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class OwnerRepositoryImpl implements OwnerRepository {

    private final OwnerMongoRepository ownerMongoRepository;
    private final ModelMapper modelMapper;

    @Override
    public Owner findById(String ownerId) {
        Optional<OwnerEntity> ownerEntity = ownerMongoRepository.findById(ownerId);

        if (ownerEntity.isEmpty()) {
            throw new EntityNotFoundException("Owner with id " + ownerId + " doesn't  found ");
        }

        return modelMapper.map(ownerEntity.get(), Owner.class);
    }

    @Override
    public Owner save(Owner owner) {
        OwnerEntity ownerEntity = modelMapper.map(owner, OwnerEntity.class);

        OwnerEntity savedOwnerEntity = ownerMongoRepository.save(ownerEntity);

        return modelMapper.map(savedOwnerEntity, Owner.class);
    }
}
