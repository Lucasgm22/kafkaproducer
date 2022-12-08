package com.poc.springkafkapoc.service;

import com.poc.springkafkapoc.data.MessageDLT;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProducerDLT {

    private final KafkaTemplate<MessageDLT.Key, MessageDLT.Value> template;




    public void produce(MessageDLT messageDLT) {
        template.send("quickstart-events-DLT",
                messageDLT.getKey(),
                messageDLT.getValue());
    }
}
