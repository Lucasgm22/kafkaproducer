package com.poc.springkafkapoc.service;

import com.poc.springkafkapoc.data.Message;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.concurrent.CountDownLatch;

@Service
@Slf4j
@Getter
public class Consumer {

    private Message currentMessage;

    private CountDownLatch latch = new CountDownLatch(1);

    @KafkaListener(topics = "quickstart-events", groupId = "poc")
    public void consume(@Header(KafkaHeaders.RECEIVED_KEY) Message.Key key,
                        @Payload Message.Value value) {
        currentMessage = Message.builder()
                .key(key)
                .value(value)
                .build();
        latch.countDown();
        log.info("Message received: [key: {}, content: {}]", key, value);
    }

    public void resetLatch() {
        latch = new CountDownLatch(1);
    }
}
