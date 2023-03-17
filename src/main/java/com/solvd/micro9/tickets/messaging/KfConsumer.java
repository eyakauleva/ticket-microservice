package com.solvd.micro9.tickets.messaging;

import com.solvd.micro9.tickets.service.TicketService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.reactive.ReactiveKafkaConsumerTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class KfConsumer {

    private final ReactiveKafkaConsumerTemplate<String, Long> receiver;
    private final TicketService ticketService;

    @PostConstruct
    public void fetch() {
        receiver.receive()
                .subscribe(record -> {
                    log.info("received message: {}", record);
                    ticketService.updateDeletedUserTickets(record.value());
                    record.receiverOffset().acknowledge();
                });
    }

}