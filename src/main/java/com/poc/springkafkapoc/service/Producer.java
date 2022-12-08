package com.poc.springkafkapoc.service;

import com.poc.springkafkapoc.data.Message;
import com.poc.springkafkapoc.data.MessageDLT;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.listener.RetryListenerSupport;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class Producer {

    private final KafkaTemplate<Message.Key, Message.Value> template;

    private final ProducerDLT producerDLT;


    @Bean
    public RetryTemplate myRetryTemplate() {

        RetryTemplate retryTemplate = new RetryTemplate();

        retryTemplate.registerListener(new RetryListenerSupport() {

            @Override
            public <T, E extends Throwable> void onError(RetryContext context, RetryCallback<T, E> callback,
                                                         Throwable throwable) {
                log.error("Tentativa {}. Motivo: {}", context.getRetryCount(), throwable.getMessage());
            }
        });

        return retryTemplate;
    }

    public void produce(Message message) {

        myRetryTemplate().execute(context -> {
                    throw new RuntimeException("Run time");
                    //sendToTopic(message);
                    //return message;
                },
                context -> {
                    producerDLT.produce(
                            MessageDLT.builder()
                                    .key(MessageDLT.Key.builder()
                                            .id(message.getKey().getId())
                                            .build())
                                    .value(MessageDLT.Value.builder()
                                            .stackTrace(ExceptionUtils.getStackTrace(context.getLastThrowable()))
                                            .value(message.getValue())
                                            .build())
                                    .build());
                    log.error("Falha na integração de Produto. Realizadas {} tentativas.", context.getRetryCount(), context.getLastThrowable());
                    return message;
                }
                );
    }

    public void sendToTopic(Message message) {
                template.send("quickstart-events",
                       message.getKey(),
                       message.getValue());
    }
    

}
