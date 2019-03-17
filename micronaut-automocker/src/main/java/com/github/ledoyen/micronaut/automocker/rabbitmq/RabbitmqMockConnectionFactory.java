package com.github.ledoyen.micronaut.automocker.rabbitmq;

import javax.inject.Singleton;

import io.micronaut.configuration.rabbitmq.connect.RabbitConnectionFactoryConfig;
import io.micronaut.context.annotation.Replaces;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.fridujo.rabbitmq.mock.MockConnectionFactory;

@Singleton
@Replaces(bean = RabbitConnectionFactoryConfig.class)
public class RabbitmqMockConnectionFactory extends MockConnectionFactory {

    private final Logger logger = LoggerFactory.getLogger(RabbitmqMockConnectionFactory.class);

    public RabbitmqMockConnectionFactory() {
        logger.debug("Mocking {}", RabbitConnectionFactoryConfig.class.getSimpleName());
    }
}
