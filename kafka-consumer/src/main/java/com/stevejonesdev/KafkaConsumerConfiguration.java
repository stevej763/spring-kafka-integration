package com.stevejonesdev;

import com.stevejonesdev.handler.CustomMessage;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class KafkaConsumerConfiguration {

    @Value(value = "${kafka.bootstrapAddress}")
    private String bootstrapAddress;

    @Value(value = "${kafka.groupId}")
    private String groupId;

    @Value(value = "${kafka.clientId}")
    private String clientId;

    @Bean
    public ConsumerFactory<String, String> stringConsumerFactory() {
        Map<String, Object> consumerProperties = new HashMap<>();
        consumerProperties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        consumerProperties.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        consumerProperties.put(ConsumerConfig.CLIENT_ID_CONFIG, clientId);
        consumerProperties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        consumerProperties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        return new DefaultKafkaConsumerFactory<>(consumerProperties);
    }

    @Bean
    public ConsumerFactory<String, CustomMessage> jsonConsumerFactory() {
        Map<String, Object> consumerProperties = new HashMap<>();
        consumerProperties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        consumerProperties.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        consumerProperties.put(ConsumerConfig.CLIENT_ID_CONFIG, clientId);
        consumerProperties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        consumerProperties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        JsonDeserializer<CustomMessage> jsonDeserializer = new JsonDeserializer<>(CustomMessage.class);
        jsonDeserializer.setRemoveTypeHeaders(false);
        jsonDeserializer.addTrustedPackages("com.stevejonesdev.*");
        jsonDeserializer.setUseTypeMapperForKey(true);

        return new DefaultKafkaConsumerFactory<>(consumerProperties,
                new StringDeserializer(),
                jsonDeserializer);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaStringListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(stringConsumerFactory());
        return factory;
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, CustomMessage> kafkaJsonListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, CustomMessage> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(jsonConsumerFactory());
        return factory;
    }


}
