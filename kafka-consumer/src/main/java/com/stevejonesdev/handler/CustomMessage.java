package com.stevejonesdev.handler;

import com.fasterxml.jackson.annotation.JsonProperty;

import static org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString;

public class CustomMessage {

    private final String from;
    private final String to;
    private final String content;

    public CustomMessage(@JsonProperty("from") String from, @JsonProperty("to") String to, @JsonProperty("content") String content) {
        this.from = from;
        this.to = to;
        this.content = content;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return reflectionToString(this);
    }
}
