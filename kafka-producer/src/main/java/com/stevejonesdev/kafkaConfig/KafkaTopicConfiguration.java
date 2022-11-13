package com.stevejonesdev.kafkaConfig;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.core.KafkaAdmin.NewTopics;

import java.util.HashMap;

@Configuration
public class KafkaTopicConfiguration {

    @Value(value = "${kafka.bootstrapAddress}")
    private String bootstrapAddress;

    @Value(value = "${kafka.stringTopic}")
    private String stringTopic;

    @Value(value = "${kafka.jsonTopic}")
    private String jsonTopic;

    @Bean
    public KafkaAdmin kafkaAdmin() {
        HashMap<String, Object> config = new HashMap<>();
        config.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        return new KafkaAdmin(config);
    }

    @Bean
    public NewTopics topics() {
        return new NewTopics(
                new NewTopic("test", 2, (short) 1),
                new NewTopic(stringTopic, 3, (short) 1),
                new NewTopic(jsonTopic, 2, (short) 1));
    }
}
