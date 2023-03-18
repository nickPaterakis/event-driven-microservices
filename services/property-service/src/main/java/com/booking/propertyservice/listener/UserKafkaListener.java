package com.booking.propertyservice.listener;

import com.booking.domain.event.user.UserEvent;
import com.booking.kafkaconsumer.service.consumer.KafkaConsumer;
import com.booking.propertyservice.service.ownerservice.OwnerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserKafkaListener implements KafkaConsumer<UserEvent> {

    private final OwnerService ownerService;

    @KafkaListener(topics = "Booking.User_Service.User",
                   groupId = "property-service-consumer-group",
                   properties = {"spring.json.value.default.type=com.booking.domain.event.user.UserEvent"})
    public void receive(@Payload List<UserEvent> messages,
                        @Header(KafkaHeaders.RECEIVED_KEY) List<String> keys,
                        @Header(KafkaHeaders.RECEIVED_PARTITION) List<Integer> partitions,
                        @Header(KafkaHeaders.OFFSET) List<Long> offsets) {
        log.info("{} number of user updated requests received with keys:{}, partitions:{} and offsets: {}",
                messages.size(),
                keys.toString(),
                partitions.toString(),
                offsets.toString());
        messages.forEach(ownerService::updateOwner);
    }
}
