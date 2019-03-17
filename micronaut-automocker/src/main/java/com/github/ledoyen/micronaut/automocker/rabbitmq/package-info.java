@Configuration
@Requires(property = "automocker.enabled")
@Requires(classes = RabbitConnectionFactoryConfig.class)
package com.github.ledoyen.micronaut.automocker.rabbitmq;

import io.micronaut.configuration.rabbitmq.connect.RabbitConnectionFactoryConfig;
import io.micronaut.context.annotation.Configuration;
import io.micronaut.context.annotation.Requires;
