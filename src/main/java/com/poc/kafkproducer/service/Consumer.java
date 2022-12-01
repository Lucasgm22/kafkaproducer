package com.poc.kafkproducer.service;

import com.poc.kafkproducer.data.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class Consumer {

    @KafkaListener(topics = "quickstart-events", groupId = "poc")
    public void consume(@Header(KafkaHeaders.RECEIVED_KEY) Message.Key key, @Payload Message.Value message) {
        log.info("Message key: {}, content: {}", key, message);
    }
}
