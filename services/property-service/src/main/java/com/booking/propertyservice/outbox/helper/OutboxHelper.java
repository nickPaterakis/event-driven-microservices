package com.booking.propertyservice.outbox.helper;

import com.booking.domain.event.property.PropertyEvent;
import com.booking.propertyservice.model.Property;
import com.booking.propertyservice.outbox.model.Outbox;
import com.booking.propertyservice.repository.OutboxRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class OutboxHelper {

    private final OutboxRepository outboxRepository;
    private final ModelMapper modelMapper;

    private static final String PROPERTY_DELETED = "PropertyDeleted";
    private static final String PROPERTY_TOPIC = "Property_Service.Property";

    @Transactional
    public void saveOutboxMessage(Property property) {
        log.info("Save property event to outbox table with property id {}", property.getId());

        Outbox outbox = outboxRepository.save(createOutboxEvent(property));

        outboxRepository.delete(outbox);
    }

    private Outbox createOutboxEvent(Property property) {
        ObjectMapper mapper = new ObjectMapper();
        PropertyEvent propertyEvent = modelMapper.map(property, PropertyEvent.class);
        JsonNode jsonNode = mapper.convertValue(propertyEvent, JsonNode.class);

        return Outbox.builder()
                .id(UUID.randomUUID().toString())
                .type(PROPERTY_DELETED)
                .aggregateType(PROPERTY_TOPIC)
                .aggregateId(property.getId())
                .payload(jsonNode.toString())
                .build();
    }
}
