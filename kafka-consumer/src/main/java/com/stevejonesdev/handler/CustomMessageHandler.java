package com.stevejonesdev.handler;

import org.springframework.kafka.annotation.KafkaListener;

public class CustomMessageHandler {

    @KafkaListener(containerFactory = "kafkaJsonListenerContainerFactory", topics = "JSON_TOPIC")
    public void handleCustomMessage(CustomMessage customMessage) {
        System.out.println(customMessage);
    }
}
