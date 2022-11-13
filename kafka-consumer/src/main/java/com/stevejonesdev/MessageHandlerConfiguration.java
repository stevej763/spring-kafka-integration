package com.stevejonesdev;

import com.stevejonesdev.handler.CustomMessageHandler;
import com.stevejonesdev.handler.DefaultMessageHandler;
import com.stevejonesdev.handler.EnvironmentMessageHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageHandlerConfiguration {

    @Bean
    public EnvironmentMessageHandler kafkaMessageHandler() {
        return new EnvironmentMessageHandler();
    }

    @Bean
    public DefaultMessageHandler defaultMessageHandler() {
        return new DefaultMessageHandler();
    }

    @Bean
    public CustomMessageHandler customMessageHandler() {
        return new CustomMessageHandler();
    }
}
