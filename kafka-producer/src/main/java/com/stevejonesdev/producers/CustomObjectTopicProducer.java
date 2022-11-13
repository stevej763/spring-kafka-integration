package com.stevejonesdev.producers;

import com.stevejonesdev.api.CustomMessage;
import org.springframework.kafka.core.KafkaTemplate;

public class CustomObjectTopicProducer {

    private final KafkaTemplate<String, CustomMessage> kafkaJsonTemplate;
    private final String topic;

    public CustomObjectTopicProducer(KafkaTemplate<String, CustomMessage> kafkaJsonTemplate, String topic) {
        this.kafkaJsonTemplate = kafkaJsonTemplate;
        this.topic = topic;
    }

    public void sendMessage(CustomMessage message) {
        kafkaJsonTemplate.send(topic, message);
    }
}
