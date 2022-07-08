package com.learn.kafka.controller;

import com.learn.kafka.dto.MessageRequest;
import com.learn.kafka.dto.kafka.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/messages")
public class MessageController {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final KafkaTemplate<String, Message> kafkaMessageTemplate;

    @Autowired
    public MessageController(KafkaTemplate<String, String> kafkaTemplate, KafkaTemplate<String, Message> kafkaMessageTemplate) {
        this.kafkaTemplate = kafkaTemplate;
        this.kafkaMessageTemplate = kafkaMessageTemplate;
    }

    @PostMapping
    public void publishMessage(@RequestBody MessageRequest messageRequest) {
        kafkaTemplate.send("main", messageRequest.message());
    }

    @PostMapping("/notification")
    public void publishMessageNotification(@RequestBody MessageRequest messageRequest) {
        kafkaMessageTemplate.send("main",
                new Message(messageRequest.title(), messageRequest.message()));
    }
}
