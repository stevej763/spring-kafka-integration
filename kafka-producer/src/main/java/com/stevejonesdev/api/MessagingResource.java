package com.stevejonesdev.api;

import com.stevejonesdev.producers.CustomObjectTopicProducer;
import com.stevejonesdev.producers.TopicOneProducer;
import com.stevejonesdev.producers.TestMessageProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/kafka")
public class MessagingResource {

    @Autowired
    private final TestMessageProducer messageProducer;
    @Autowired
    private final TopicOneProducer customTopicMessageProducer;
    @Autowired
    private final CustomObjectTopicProducer customObjectTopicProducer;

    private final List<Integer> messages;

    public MessagingResource(
            TestMessageProducer messageProducer,
            TopicOneProducer customTopicMessageProducer, CustomObjectTopicProducer customObjectTopicProducer) {
        this.messageProducer = messageProducer;
        this.customTopicMessageProducer = customTopicMessageProducer;
        this.customObjectTopicProducer = customObjectTopicProducer;
        this.messages = new ArrayList<>();
    }

    @GetMapping("/send/test")
    public ResponseEntity<String> sendMessage() {
        messages.add(messages.size());
        String message = "I have a message for you: " + messages.get(messages.size() - 1);
        messageProducer.sendMessage(message);
        return ResponseEntity.ok("sent message: " + message);
    }

    @GetMapping("/send/custom")
    public ResponseEntity<String> sendMessage(@RequestParam("message") String message) {
        customTopicMessageProducer.sendMessage("I have a message for you: " + message);
        return ResponseEntity.ok(String.format("message: %s", message));
    }

    @PostMapping("send/object")
    public ResponseEntity<CustomMessage> sendMessage(@RequestBody CustomMessage message) {
        customObjectTopicProducer.sendMessage(message);
        return ResponseEntity.ok(message);
    }
}
