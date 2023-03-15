package com.solvd.micro9.tickets.messaging;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.reactive.ReactiveKafkaConsumerTemplate;
import reactor.kafka.receiver.ReceiverOptions;

import java.util.Collections;

@Slf4j
@Configuration
public class KfConsumerConfig {

    @Value("${spring.kafka.topic}")
    private String topic;

    @Bean
    public ReceiverOptions<String, Long> receiverOptions(KafkaProperties kafkaProperties) {
        ReceiverOptions<String, Long> basicReceiverOptions = ReceiverOptions.create(kafkaProperties.buildConsumerProperties());
        return basicReceiverOptions.subscription(Collections.singletonList(topic))
                .addAssignListener(receiverPartitions -> log.info("AssignListener: {}", receiverPartitions))
                .addRevokeListener(receiverPartitions -> log.info("RevokeListener: {}", receiverPartitions));
    }

    @Bean
    public ReactiveKafkaConsumerTemplate<String, Long> consumer(ReceiverOptions<String, Long> receiverOptions) {
        return new ReactiveKafkaConsumerTemplate<>(receiverOptions);
    }

}
