package com.stevejonesdev.producers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;

public class TopicOneProducer {

    @Autowired
    private final KafkaTemplate<String, String> kafkaTemplate;

    private final String topic;

    public TopicOneProducer(KafkaTemplate<String, String> kafkaTemplate, String topic) {
        this.kafkaTemplate = kafkaTemplate;
        this.topic = topic;
    }

    public void sendMessage(String message) {
        kafkaTemplate.send(topic, message);
    }
}
