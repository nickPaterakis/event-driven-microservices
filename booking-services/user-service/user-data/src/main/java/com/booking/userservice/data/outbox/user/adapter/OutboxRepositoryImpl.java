package com.booking.userservice.data.outbox.user.adapter;

import com.booking.userservice.data.outbox.user.mapper.OutboxMapper;
import com.booking.userservice.data.outbox.user.repository.OutboxMongoRepository;
import com.booking.userservice.domain.outbox.model.UserOutboxEvent;
import com.booking.userservice.domain.repository.OutboxRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OutboxRepositoryImpl implements OutboxRepository {

    private final OutboxMongoRepository outboxMongoRepository;
    private final OutboxMapper outboxMapper;

    @Override
    public UserOutboxEvent save(UserOutboxEvent userOutboxEvent) {
        return outboxMapper.outboxToUserOutboxEvent(
                outboxMongoRepository.save(outboxMapper.userOutboxEventToOutbox(userOutboxEvent)));
    }

    @Override
    public void delete(UserOutboxEvent userOutboxEvent) {
        outboxMongoRepository.delete(outboxMapper.userOutboxEventToOutbox(userOutboxEvent));
    }
}