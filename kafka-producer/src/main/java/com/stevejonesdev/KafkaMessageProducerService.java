package com.stevejonesdev;

import org.apache.kafka.common.protocol.types.Field;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KafkaMessageProducerService {

    public static void main(String[] args) {
        SpringApplication.run(KafkaMessageProducerService.class, args);

    }
}
