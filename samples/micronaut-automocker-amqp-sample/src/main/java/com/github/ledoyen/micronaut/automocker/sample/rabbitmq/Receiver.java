package com.github.ledoyen.micronaut.automocker.sample.rabbitmq;

import java.util.ArrayList;
import java.util.List;

import io.micronaut.configuration.rabbitmq.annotation.Queue;
import io.micronaut.configuration.rabbitmq.annotation.RabbitListener;

@RabbitListener
public class Receiver {

    private final List<String> messages = new ArrayList<>();

    @Queue(RabbitmqConfiguration.QUEUE_NAME)
    public void receive(String message) {
        System.out.println("Received <" + message + ">");
        this.messages.add(message);
    }

    public List<String> getMessages() {
        return messages;
    }
}
