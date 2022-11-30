package com.poc.kafkproducer.controller;

import com.poc.kafkproducer.data.PersonMessage;
import com.poc.kafkproducer.service.PersonProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/person")
@RequiredArgsConstructor
public class PersonController {

    private final PersonProducer personProducer;

    @PostMapping
    public ResponseEntity<Void> sendToTopic(@RequestBody PersonMessage personMessage) {

        personProducer.produce(personMessage);

        return ResponseEntity.ok().build();
    }

}
