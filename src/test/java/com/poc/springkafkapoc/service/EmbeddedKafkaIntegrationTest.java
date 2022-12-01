package com.poc.springkafkapoc.service;

import com.poc.springkafkapoc.data.Message;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DirtiesContext
@EmbeddedKafka(partitions = 1, brokerProperties = { "listeners=PLAINTEXT://localhost:9092", "port=9092" })
class EmbeddedKafkaIntegrationTest {

    @Autowired
    private Consumer consumer;

    @Autowired
    private Producer producer;

    @Value("${test.topic}")
    private String topic;

    @Test
    void givenEmbeddedKafkaBroker_whenSendingWithSimpleProducer_thenMessageReceived() throws Exception {
        Message message = Message.builder()
                .key(Message.Key.builder()
                        .id(1)
                        .build())
                .value(Message.Value.builder()
                        .content("content")
                        .build())
                .build();

        producer.produce(topic, message);

        boolean messageConsumed = consumer.getLatch().await(30, TimeUnit.SECONDS);
        assertTrue(messageConsumed);
        assertEquals(message, consumer.getCurrentMessage());
    }
}