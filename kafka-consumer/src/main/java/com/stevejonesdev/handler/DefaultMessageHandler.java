package com.stevejonesdev.handler;

public class DefaultMessageHandler {

//    @KafkaListener(containerFactory = "kafkaStringListenerContainerFactory", topics = "test")
    public void handleMessage(String message) {
        System.out.println("default message received:" + message);
    }
}
