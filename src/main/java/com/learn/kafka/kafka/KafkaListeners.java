package com.learn.kafka.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaListeners {

    @KafkaListener(topics = "main", groupId = "mainGroup")
    void listener(String data) {
        System.out.printf("Listener received: %s%n", data);
    }

}
