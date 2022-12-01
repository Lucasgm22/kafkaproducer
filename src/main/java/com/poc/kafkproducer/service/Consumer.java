package com.poc.kafkproducer.service;

import com.poc.kafkproducer.data.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Slf4j
public class Consumer {

    @KafkaListener(topics = "quickstart-events", groupId = "poc")
    public void consume(Message.Value message) {
        log.info("Message received: {}", message);
    }
}
