package com.github.ledoyen.micronaut.automocker.sample.rabbitmq;

import static java.time.Duration.ofMillis;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;

import com.github.ledoyen.micronaut.automocker.Automocker;
import org.junit.jupiter.api.Test;

@Automocker
class RabbitmqApplicationIntegrationTest {

    @Inject
    Sender sender;

    @Inject
    Receiver receiver;

    @Test
    void message_is_received_when_sent_by_sender() {
        String message = UUID.randomUUID().toString();
        sender.send(message);
        List<String> receivedMessages = new ArrayList<>();
        assertTimeoutPreemptively(ofMillis(500L), () -> {
                while (receivedMessages.isEmpty()) {
                    receivedMessages.addAll(receiver.getMessages());
                    TimeUnit.MILLISECONDS.sleep(100L);
                }
            }
        );

        assertThat(receivedMessages).containsExactly(message);
    }
}
