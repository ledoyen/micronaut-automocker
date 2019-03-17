package com.github.ledoyen.micronaut.automocker.sample.rabbitmq;

import java.io.IOException;
import javax.inject.Singleton;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import io.micronaut.configuration.rabbitmq.connect.ChannelInitializer;

@Singleton
public class RabbitmqConfiguration extends ChannelInitializer {

    final static String QUEUE_NAME = "micronaut";
    final static String EXCHANGE_NAME = "micronaut-ex";

    @Override
    public void initialize(Channel channel) throws IOException {
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.TOPIC);

        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "fruit.*");
    }
}
