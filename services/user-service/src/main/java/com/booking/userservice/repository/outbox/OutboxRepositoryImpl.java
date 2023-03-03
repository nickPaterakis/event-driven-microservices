package com.booking.userservice.repository.outbox;

import com.booking.userservice.outbox.entity.UserOutboxEvent;
import com.booking.userservice.repository.outbox.entity.OutboxEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OutboxRepositoryImpl implements OutboxRepository {

    private final OutboxMongoRepository outboxMongoRepository;
    private final ModelMapper modelMapper;

    @Override
    public UserOutboxEvent save(UserOutboxEvent userOutboxEvent) {
        OutboxEntity outboxEntity = outboxMongoRepository.save(modelMapper.map(userOutboxEvent, OutboxEntity.class));
        return modelMapper.map(outboxEntity, UserOutboxEvent.class);

    }

    @Override
    public void delete(UserOutboxEvent userOutboxEvent) {
        outboxMongoRepository.delete(modelMapper.map(userOutboxEvent, OutboxEntity.class));
    }
}