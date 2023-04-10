package com.solvd.micro9.tickets.messaging;

import com.solvd.micro9.tickets.domain.command.ProcessTicketUpdateCommand;
import com.solvd.micro9.tickets.domain.command.DeleteTicketsUserByUserIdCommand;
import com.solvd.micro9.tickets.domain.es.Es;
import com.solvd.micro9.tickets.domain.es.EsStatus;
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

    private final ReactiveKafkaConsumerTemplate<String, Es> receiver;
    private final EsTicketCommandHandler commandHandler;

    @PostConstruct
    public void fetch() {
        receiver.receive()
                .subscribe(record -> {
                    log.info("received value: {}", record.value());
                    if (EsStatus.PENDING.equals(
                            record.value().getStatus()
                    )) {
                        DeleteTicketsUserByUserIdCommand command =
                                new DeleteTicketsUserByUserIdCommand(
                                        record.value().getEntityId()
                                );
                        commandHandler.apply(command);
                    } else {
                        ProcessTicketUpdateCommand command =
                                new ProcessTicketUpdateCommand(
                                        record.value().getEntityId(),
                                        record.value().getStatus()
                                );
                        commandHandler.apply(command);
                    }
                    record.receiverOffset().acknowledge();
                });
    }

}
