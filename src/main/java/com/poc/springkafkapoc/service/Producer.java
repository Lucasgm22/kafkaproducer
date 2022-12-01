package com.poc.springkafkapoc.service;

import com.poc.springkafkapoc.data.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class Producer {

    private final KafkaTemplate<Message.Key, Message.Value> template;

    public void produce(Message message) {
        template.send("quickstart-events",
                       message.getKey(),
                       message.getValue());
    }

}
