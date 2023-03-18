package com.booking.kafkaproducer.service.producer;

import org.springframework.kafka.support.ProducerListener;

public interface KafkaProducer<K, V> {
    void send(String topicName, K key, V message, ProducerListener<K, V> callback);
}
