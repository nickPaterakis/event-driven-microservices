package com.booking.userservice.outbox.helper;

import com.booking.userservice.model.User;
import com.booking.userservice.outbox.entity.UserOutboxEvent;
import com.booking.userservice.repository.outbox.OutboxRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class OutboxHelper {

    private final OutboxRepository outboxRepository;

    private static final String USER_UPDATED = "UserUpdated";
    private static final String USER_TOPIC = "User_Service.User";

    @Transactional
    public void saveOutboxMessage(User user) {
        log.info("Save user event to outbox table with user id {}", user.getId());

        UserOutboxEvent savedUserOutboxEvent = outboxRepository.save(createUserOutboxEvent(user));

        outboxRepository.delete(savedUserOutboxEvent);
    }

    public UserOutboxEvent createUserOutboxEvent(User user) {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.convertValue(user, JsonNode.class);

        return UserOutboxEvent.builder()
                .id(UUID.randomUUID().toString())
                .type(USER_UPDATED)
                .aggregateType(USER_TOPIC)
                .aggregateId(user.getId())
                .payload(jsonNode.toString())
                .build();
    }
}
