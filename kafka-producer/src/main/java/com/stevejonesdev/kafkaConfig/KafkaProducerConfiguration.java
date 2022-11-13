package com.stevejonesdev.kafkaConfig;

import com.stevejonesdev.api.CustomMessage;
import com.stevejonesdev.producers.CustomObjectTopicProducer;
import com.stevejonesdev.producers.TopicOneProducer;
import com.stevejonesdev.producers.TestMessageProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfiguration {

    @Value(value = "${kafka.bootstrapAddress}")
    private String bootstrapAddress;

    @Value(value = "${kafka.stringTopic}")
    private String stringTopic;

    @Value(value = "${kafka.jsonTopic}")
    private String jsonTopic;

    @Bean
    public ProducerFactory<String, String> stringProducerFactory() {
        Map<String, Object> configurationProperties = new HashMap<>();
        configurationProperties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        configurationProperties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configurationProperties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        return new DefaultKafkaProducerFactory<>(configurationProperties);
    }

    @Bean
    public ProducerFactory<String, CustomMessage> jsonProducerFactory() {
        Map<String, Object> configurationProperties = new HashMap<>();
        configurationProperties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        configurationProperties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configurationProperties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return new DefaultKafkaProducerFactory<>(configurationProperties);
    }

    @Bean
    public KafkaTemplate<String, String> kafkaStringTemplate() {
        return new KafkaTemplate<>(stringProducerFactory());
    }

    @Bean
    public KafkaTemplate<String, CustomMessage> kafkaJsonTemplate() {
        return new KafkaTemplate<>(jsonProducerFactory());
    }

    @Bean
    public TestMessageProducer testMessageProducer() {
        return new TestMessageProducer(kafkaStringTemplate(), "test");
    }

    @Bean
    public TopicOneProducer topicOneProducer() {
        System.out.println("Topic from environment: " + stringTopic);
        return new TopicOneProducer(kafkaStringTemplate(), stringTopic);
    }

    @Bean
    public CustomObjectTopicProducer customObjectTopicProducer() {
        System.out.println("message producer for a custom Message");
        return new CustomObjectTopicProducer(kafkaJsonTemplate(), jsonTopic);
    }
}
