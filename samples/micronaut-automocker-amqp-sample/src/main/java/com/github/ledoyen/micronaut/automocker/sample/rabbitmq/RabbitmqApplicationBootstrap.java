package com.github.ledoyen.micronaut.automocker.sample.rabbitmq;

import java.util.concurrent.TimeUnit;

import io.micronaut.context.ApplicationContext;
import io.micronaut.runtime.Micronaut;

public class RabbitmqApplicationBootstrap {

    public static void main(String[] args) throws InterruptedException {
        try (ApplicationContext context = Micronaut.run(RabbitmqApplicationBootstrap.class)) {
            context.getBean(Sender.class).send("Hello RabbitMQ!");
            Receiver receiver = context.getBean(Receiver.class);
            while (receiver.getMessages().isEmpty()) {
                TimeUnit.MILLISECONDS.sleep(100L);
            }
        }
    }
}
