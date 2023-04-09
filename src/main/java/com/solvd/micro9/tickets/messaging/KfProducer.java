package com.solvd.micro9.tickets.messaging;

import com.solvd.micro9.tickets.domain.es.Es;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.reactive.ReactiveKafkaProducerTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KfProducer {

    @Value("${spring.kafka.producer.topic}")
    private String topic;

    private final ReactiveKafkaProducerTemplate<String, Es> producer;

    public void send(String key, Es eventStore) {
        producer.send(topic, key, eventStore)
                .subscribe();
    }

}
