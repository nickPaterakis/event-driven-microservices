package com.booking.kafkaproducer.service.producer.helper;


import com.booking.domain.model.outbox.OutboxStatus;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.kafka.support.ProducerListener;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.function.BiConsumer;

@Slf4j
@Component
public class KafkaMessageHelper {

    public <T, U> ProducerListener<String, T>
    getKafkaCallback(String responseTopicName, T avroModel, U outboxMessage,
                     BiConsumer<U, OutboxStatus> outboxCallback,
                     String orderId, String model) {
        return new ProducerListener<String, T>() {
            @Override
            public void onSuccess(ProducerRecord<String, T> producerRecord, RecordMetadata recordMetadata) {
                log.info("Received successful response from Kafka for order id: {}" +
                                " Topic: {} Partition: {} Offset: {} Timestamp: {}",
                        orderId,
                        recordMetadata.topic(),
                        recordMetadata.partition(),
                        recordMetadata.offset(),
                        recordMetadata.timestamp());
                outboxCallback.accept(outboxMessage, OutboxStatus.COMPLETED);
            }

            @Override
            public void onError(ProducerRecord<String, T> producerRecord, RecordMetadata recordMetadata, Exception exception) {
                log.error("Error while sending {} with message: {} and outbox type: {} to topic {}",
                        model, avroModel.toString(), outboxMessage.getClass().getName(), responseTopicName, exception);
                outboxCallback.accept(outboxMessage, OutboxStatus.FAILED);
            }
        };
    }
}
