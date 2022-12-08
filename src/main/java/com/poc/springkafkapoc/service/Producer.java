package com.poc.springkafkapoc.service;

import com.poc.springkafkapoc.data.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class Producer {

    private final KafkaTemplate<Message.Key, Message.Value> template;

    @Value("${test.topic}")
    private final String TOPIC_NAME;

    public void produce(Message message) {
        template.send(TOPIC_NAME,
                       message.getKey(),
                       message.getValue());
    }

}
