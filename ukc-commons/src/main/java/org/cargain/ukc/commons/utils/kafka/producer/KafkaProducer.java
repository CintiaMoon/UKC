package org.cargain.ukc.commons.utils.kafka.producer;

import org.cargain.ukc.commons.events.Event;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class KafkaProducer{
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public <T extends Event> void sendMessage(T event, String topic){
        log.info(String.format("Event => %s", event.toString()));
        Message<T> message = MessageBuilder
                .withPayload(event)
                .setHeader(KafkaHeaders.TOPIC, topic)
                .build();

        kafkaTemplate.send(message);
    }
}
