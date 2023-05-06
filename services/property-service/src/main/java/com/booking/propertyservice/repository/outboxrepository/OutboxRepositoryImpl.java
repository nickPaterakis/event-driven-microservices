package com.booking.propertyservice.repository.outboxrepository;

import com.booking.propertyservice.outbox.model.Outbox;
import com.booking.propertyservice.repository.outboxrepository.entity.OutboxEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OutboxRepositoryImpl implements OutboxRepository {
    private final OutboxMongoRepository outboxMongoRepository;
    private final ModelMapper modelMapper;

    @Override
    public Outbox save(Outbox outbox) {
        OutboxEntity outboxEntity = modelMapper.map(outbox, OutboxEntity.class);
        return modelMapper.map(outboxMongoRepository.save(outboxEntity), Outbox.class);
    }

    @Override
    public void delete(Outbox outbox) {
        OutboxEntity outboxEntity = modelMapper.map(outbox, OutboxEntity.class);
        outboxMongoRepository.delete(outboxEntity);
    }
}
