package com.stevejonesdev.handler;

public class EnvironmentMessageHandler {

//    @KafkaListener(containerFactory = "kafkaStringListenerContainerFactory", topics = "${kafka.topic}")
    public void handleMessage(String message) {
        System.out.println("message received:" + message);
    }
}
