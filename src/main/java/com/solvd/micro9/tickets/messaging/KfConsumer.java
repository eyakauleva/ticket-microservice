package com.solvd.micro9.tickets.messaging;

import com.solvd.micro9.tickets.domain.command.SetTicketsUserIdToNullByUserIdCommand;
import com.solvd.micro9.tickets.service.EsTicketCommandHandler;
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
    private final EsTicketCommandHandler commandHandler;

    @PostConstruct
    public void fetch() {
        receiver.receive()
                .subscribe(record -> {
                    log.info("received value: {}", record.value());
                    SetTicketsUserIdToNullByUserIdCommand command = new SetTicketsUserIdToNullByUserIdCommand(
                            record.value()
                    );
                    commandHandler.apply(command);
                    record.receiverOffset().acknowledge();
                });
    }

}
