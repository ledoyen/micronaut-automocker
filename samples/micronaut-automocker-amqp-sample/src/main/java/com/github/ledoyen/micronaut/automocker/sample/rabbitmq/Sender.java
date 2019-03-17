package com.github.ledoyen.micronaut.automocker.sample.rabbitmq;

import io.micronaut.configuration.rabbitmq.annotation.Binding;
import io.micronaut.configuration.rabbitmq.annotation.RabbitClient;

@RabbitClient(RabbitmqConfiguration.EXCHANGE_NAME)
public interface Sender {

    @Binding("fruit.orange")
    void send(String message);
}
