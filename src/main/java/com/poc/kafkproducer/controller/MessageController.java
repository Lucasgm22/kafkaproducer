package com.poc.kafkproducer.controller;

import com.poc.kafkproducer.data.Message;
import com.poc.kafkproducer.service.PersonProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/message")
@RequiredArgsConstructor
public class MessageController {

    private final PersonProducer personProducer;

    @PostMapping
    public ResponseEntity<Void> sendToTopic(@RequestBody Message message) {

        personProducer.produce(message);

        return ResponseEntity.ok().build();
    }

}
