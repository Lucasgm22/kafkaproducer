package com.poc.kafkproducer.service;

import com.poc.kafkproducer.data.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class Consumer {

    @KafkaListener(topics = "quickstart-events", groupId = "poc")
    public void consume(Message.Value message) {
        log.info("Message received: {}", message);
    }
}
