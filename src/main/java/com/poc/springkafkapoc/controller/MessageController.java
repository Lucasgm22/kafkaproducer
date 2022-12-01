package com.poc.springkafkapoc.controller;

import com.poc.springkafkapoc.data.Message;
import com.poc.springkafkapoc.service.Producer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/message")
@RequiredArgsConstructor
public class MessageController {

    private final Producer producer;

    @PostMapping
    public ResponseEntity<Void> sendToTopic(@RequestParam String topic,
                                            @RequestBody Message message) {

        producer.produce(topic, message);

        return ResponseEntity.ok().build();
    }

}
