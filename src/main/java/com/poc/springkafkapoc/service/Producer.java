package com.poc.springkafkapoc.service;

import com.poc.springkafkapoc.data.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class Producer {

    private final KafkaTemplate<Message.Key, Message.Value> template;

    public void produce(String topic, Message message) {
        template.send(topic,
                       message.getKey(),
                       message.getValue());
    }

}
