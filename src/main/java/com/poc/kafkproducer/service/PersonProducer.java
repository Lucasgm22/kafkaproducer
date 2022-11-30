package com.poc.kafkproducer.service;

import com.poc.kafkproducer.data.PersonMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PersonProducer {

    private final KafkaTemplate<PersonMessage.Key, PersonMessage.Value> template;

    public void produce(PersonMessage personMessage) {
        template.send("quickstart-events",
                       personMessage.getKey(),
                       personMessage.getValue());
    }

}
